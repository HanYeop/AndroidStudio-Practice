package org.techtown.firebase_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.item_result.view.*

class ResultActivity : AppCompatActivity() {
    private var auth : FirebaseAuth? = null
    private var firestore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()

        setContentView(R.layout.activity_result)
        resultView.adapter = ResultViewRecyclerViewAdapter()
        resultView.layoutManager = LinearLayoutManager(this)
    }

    inner class ResultViewRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        var resultDTOs : ArrayList<ResultDTO> = arrayListOf()
        init{
            firestore?.collection("Test")?.orderBy("timestamp", Query.Direction.DESCENDING)?.whereEqualTo("uid",auth?.uid)?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                resultDTOs.clear()
                if(querySnapshot == null) return@addSnapshotListener

                // 데이터 받아오기
                for(snapshot in querySnapshot.documents){
                    resultDTOs.add(snapshot.toObject(ResultDTO::class.java)!!)
                }
                notifyDataSetChanged()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_result,parent,false)
            return CustomViewHolder(view)
        }

        inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        }

        override fun getItemCount(): Int {
            return resultDTOs.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as CustomViewHolder).itemView

            viewHolder.resultTextOne.text = resultDTOs!![position].textOne
            viewHolder.resultTextTwo.text = resultDTOs!![position].textTwo
        }
    }
}