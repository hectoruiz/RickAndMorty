package hector.ruiz.rickandmorty.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import hector.ruiz.presentation.list.ListViewModel
import hector.ruiz.rickandmorty.R
import hector.ruiz.rickandmorty.databinding.CharacterListBinding
import hector.ruiz.rickandmorty.extensions.snackBarIndefinite

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: CharacterListBinding? = null
    private lateinit var characterAdapter: CharacterAdapter
    private val listViewModel: ListViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CharacterListBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding?.root
    }

    private fun initRecyclerView() {
        characterAdapter = CharacterAdapter()
        binding?.charactersList?.adapter = characterAdapter
        characterAdapter.onItemClick = {
            it?.let {
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(it)
                findNavController().navigate(action)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel.getCharacterList(null)

        listViewModel.isLoading.observe(viewLifecycleOwner, {
            binding?.characterProgress?.isVisible = it
        })

        listViewModel.characterList.observe(viewLifecycleOwner, {
            characterAdapter.setList(it)
            characterAdapter.notifyItemRangeInserted(characterAdapter.itemCount, it.size)
        })

        listViewModel.errorRequest.observe(viewLifecycleOwner, {
            if (it) {
                snackBarIndefinite(R.string.error_request)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
