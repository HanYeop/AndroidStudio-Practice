package org.techtown.roomex

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.roomex.model.User
import org.techtown.roomex.databinding.LayoutItemBinding
import org.techtown.roomex.dialog.UpdateActivity

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(val binding: LayoutItemBinding) : RecyclerView.ViewHolder(binding.root)

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = LayoutItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터를 바인딩
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        val currentId = currentItem.id.toString()
        val currentName = currentItem.name
        val currentAge = currentItem.age.toString()

        holder.binding.idText.text = currentId
        holder.binding.nameText.text = currentName
        holder.binding.ageText.text = currentAge

        holder.binding.itemLayout.setOnClickListener {
            val intent = Intent(it.context,UpdateActivity::class.java)
            intent.putExtra("currentId", currentId)
            intent.putExtra("currentName", currentName)
            intent.putExtra("currentAge", currentAge)
            it.context.startActivity(intent)
        }
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return userList.size
    }

    // 유저 리스트 갱신
    fun setData(user : List<User>){
        userList = user
        notifyDataSetChanged()
    }
}