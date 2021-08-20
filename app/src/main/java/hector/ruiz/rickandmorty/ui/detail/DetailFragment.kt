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
import dagger.hilt.android.AndroidEntryPoint
import hector.ruiz.presentation.detail.DetailViewModel
import hector.ruiz.rickandmorty.R
import hector.ruiz.rickandmorty.databinding.DetailFragmentBinding
import hector.ruiz.rickandmorty.extensions.snackBarLong

@AndroidEntryPoint
class DetailFragment : DialogFragment() {

    private var _binding: DetailFragmentBinding? = null
    private val detailViewModel: DetailViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.locationAllResidents?.visibility = GONE
        detailViewModel.getLocationDetails(args.locationUrl)

        detailViewModel.isLoading.observe(viewLifecycleOwner, {
            binding?.detailsProgress?.isVisible = it
        })

        detailViewModel.locationDetails.observe(viewLifecycleOwner, {
            it?.let {
                with(binding) {
                    this?.locationName?.text = it.name
                    this?.locationDescription?.text =
                        getString(R.string.location_description, it.type, it.dimension)
                    this?.locationResidents?.text = getString(
                        R.string.location_residents,
                        getQuantityResidents(it.residents?.size)
                    )
                    this?.locationAllResidents?.visibility = VISIBLE
                }
            }
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
}
