package org.techtown.viewbinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.techtown.viewbinding.databinding.FragmentTestBinding

class TestFragment : Fragment() {

    // 메모리 관리를 위함
    private var binding : FragmentTestBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // View Binding
        binding = FragmentTestBinding.inflate(inflater,container,false)

        // root를 참조
        return binding!!.root
    }

    // 프래그먼트는 뷰보다 오래 지속 . 프래그먼트의 onDestroyView() 메서드에서 결합 클래스 인스턴스 참조를 정리
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}