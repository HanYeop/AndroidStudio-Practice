package org.techtown.roomex.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.techtown.roomex.R

class CustomDialog(context: Context, Interface: CustomDialogInterface) : Dialog(context) {

    // 액티비티에서 인터페이스를 받아옴
    private var customDialogInterface: CustomDialogInterface = Interface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_custom)

        var addButton : Button= findViewById(R.id.add_btn)
        var cancelButton : Button = findViewById(R.id.cancel_btn)
        var nameText : EditText = findViewById(R.id.nameEditView)
        var ageText : EditText = findViewById(R.id.ageEditView)

        // 배경을 투명하게함
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 추가 버튼 클릭 시 onAddButtonClicked 호출 후 종료
        addButton.setOnClickListener {

            val name = nameText.text.toString()
            var age = ageText.text.toString()

            // 입력하지 않았을 때
            if ( TextUtils.isEmpty(name) || TextUtils.isEmpty(age) ){
                Toast.makeText(context, "데이터를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

            // 입력 창이 비어 있지 않을 때
            else{
                customDialogInterface.onAddButtonClicked(name, Integer.parseInt(age))
                dismiss()
            }
        }

        // 취소 버튼 클릭 시 onCancelButtonClicked 호출 후 종료
        cancelButton.setOnClickListener {
            customDialogInterface.onCancelButtonClicked()
            dismiss()}
    }
}