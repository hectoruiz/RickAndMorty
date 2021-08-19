package hector.ruiz.rickandmorty.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import hector.ruiz.rickandmorty.R
import hector.ruiz.rickandmorty.databinding.CharacterListBinding
import hector.ruiz.usecase.usecases.GetCharactersUseCase
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: CharacterListBinding? = null
    private lateinit var characterAdapter: CharacterAdapter

    @Inject
    lateinit var getCharactersUseCase: GetCharactersUseCase

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CharacterListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Testing the api call and populate the data in the recyclerview
        runBlocking {
            getCharactersUseCase.getCharacters(null).data?.results?.let {
                characterAdapter = CharacterAdapter(it)
            }
        }
        binding?.charactersList?.adapter = characterAdapter
        characterAdapter.onItemClick = {
            it?.let {
                Log.d("LOCATION_URL", it)
            }
            findNavController().navigate(R.id.action_ListFragment_to_DetailFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
