package org.techtown.lovetest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*
import org.techtown.lovetest.R


class QuestionFragment : Fragment(), View.OnClickListener {
    lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        button_next.setOnClickListener(this); // 클래스 구현 해놓은것 쓰기
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button_next ->{
                navController.navigate(R.id.action_questionFragment_to_selectionFragment)
            }
        }
    }
}
