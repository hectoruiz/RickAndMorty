package hector.ruiz.rickandmorty.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import hector.ruiz.presentation.detail.DetailViewModel
import hector.ruiz.rickandmorty.R
import hector.ruiz.rickandmorty.databinding.LocationFragmentBinding
import hector.ruiz.rickandmorty.extensions.snackBarLong

@AndroidEntryPoint
class DetailFragment : DialogFragment() {

    private var _binding: LocationFragmentBinding? = null
    private lateinit var residentAdapter: ResidentAdapter
    private val detailViewModel: DetailViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LocationFragmentBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding?.root
    }

    private fun initRecyclerView() {
        residentAdapter = ResidentAdapter()
        binding?.residentsList?.layoutManager = GridLayoutManager(
            context,
            COLUMNS_NUMBER,
            RecyclerView.VERTICAL,
            false
        )
        binding?.residentsList?.adapter = residentAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.locationAllResidents?.visibility = GONE
        detailViewModel.getLocationDetails(args.locationUrl)

        detailViewModel.isLoading.observe(viewLifecycleOwner, {
            binding?.detailsProgress?.isVisible = it
        })

        detailViewModel.locationDetails.observe(viewLifecycleOwner, { locationDetails ->
            locationDetails?.let {
                with(binding) {
                    this?.locationName?.text = it.name
                    this?.locationDescription?.text =
                        getString(R.string.location_description, it.type, it.dimension)
                    this?.locationResidents?.text = getString(
                        R.string.location_residents,
                        getQuantityResidents(it.residents?.size)
                    )
                    with(this?.locationAllResidents) {
                        this?.visibility = VISIBLE
                        this?.isEnabled = true
                        this?.setOnClickListener {
                            detailViewModel.getAllResidents(locationDetails.residents)
                        }
                    }
                }
            }
        })

        detailViewModel.resident.observe(viewLifecycleOwner, {
            binding?.locationAllResidents?.isEnabled = false
            residentAdapter.addResident(it)
            residentAdapter.notifyItemInserted(residentAdapter.itemCount)
        })

        detailViewModel.errorRequest.observe(viewLifecycleOwner, {
            if (it) {
                snackBarLong(R.string.error_request)
            }
        })
    }

    private fun getQuantityResidents(residentNumber: Int?): String {
        val quantityResidents = residentNumber ?: 0
        return resources.getQuantityString(
            R.plurals.number_residents,
            quantityResidents, quantityResidents
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val COLUMNS_NUMBER = 2
    }
}
