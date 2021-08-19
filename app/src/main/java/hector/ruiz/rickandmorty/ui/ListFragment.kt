package hector.ruiz.rickandmorty.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import hector.ruiz.rickandmorty.R
import hector.ruiz.rickandmorty.databinding.ListFragmentBinding
import hector.ruiz.usecase.usecases.GetCharactersUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: ListFragmentBinding? = null

    @Inject lateinit var getCharactersUseCase: GetCharactersUseCase

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonNext?.setOnClickListener {
            // Testing the api call
            GlobalScope.launch {
                getCharactersUseCase.getCharacters(null).data?.results?.forEach {
                    it?.name?.let { name ->
                        Log.d("NAME", name)
                    }
                }
            }
            findNavController().navigate(R.id.action_ListFragment_to_DetailFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
