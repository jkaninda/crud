package com.github.jkantech.crudexample

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jkantech.crud.Crud
import com.github.jkantech.crud.OnResponseListener
import com.github.jkantech.crudexample.Adapter.UserAdapter
import com.github.jkantech.crudexample.models.Users
import com.github.jkantech.crudexample.utils.initUsers
import com.github.jkantech.crudexample.utils.toasMessage
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONException
import org.json.JSONObject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment(),View.OnClickListener {
 private lateinit var crud: Crud
    var usersList:Users= Users()
    lateinit var userAdapter: UserAdapter
    lateinit var user_recyclerView:RecyclerView
    lateinit var nb_users:TextView
    lateinit var dialog:Dialog

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_home, container, false)
        //init crud with (context,url)
        crud= Crud(requireContext(),url)
        user_recyclerView=root.findViewById(R.id.user_recyclerView)
        nb_users=root.findViewById(R.id.nb_users)
        dialog= Dialog(requireContext())
        val fab: FloatingActionButton =root.findViewById(R.id.fab)
        fab.setOnClickListener{
            addUserDialog()
        }

        getUsers()

        return root
    }

    private fun initView(){


    }
    //Get Users from database
    private fun getUsers(){

        //Get Users with where condition
        /*

        val conds=JSONObject()
        try {
            conds.put("id",2)

        }catch (e:JSONException){
            e.printStackTrace()
        }
        crud.get("users", conds, object : OnResponseListener{
            override fun onError(error: String?) {
                TODO("Not yet implemented")
            }

            override fun onResponse(response: String?) {
                TODO("Not yet implemented")
            }

        })


         */


        //With SQL Querry
        /*


        crud.query("SELECT * FROM `users` ORDER BY `users`.`first_name` ASC ", object : OnResponseListener{
            override fun onError(error: String?) {
                TODO("Not yet implemented")
            }

            override fun onResponse(response: String?) {
                TODO("Not yet implemented")
            }

        })


         */



            crud.get("users", null, object : OnResponseListener {


            override fun onResponse(response: String?) {
                //Convert json to ArrayList
                //initUsers comes from utils
                if (response!=null) {


                    usersList = initUsers(response)!!
                    userAdapter = UserAdapter(requireContext(), usersList, this@HomeFragment)
                    user_recyclerView.adapter = userAdapter
                    nb_users.text = usersList.size.toString()



                    toasMessage(requireContext(), response.toString())
                }else{
                    Log.d("TAG","Erreur")
                }

            }

            override fun onError(error: String?) {
                toasMessage(requireContext(),error.toString())
            }

        })
        user_recyclerView.setHasFixedSize(true)

        user_recyclerView.layoutManager=
            LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)




    }

//Add user dialog
    private fun addUserDialog() {
        dialog.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(R.layout.add_user_dialog)
            val confirmbtn = findViewById<Button>(R.id.confirm_btn)
            confirmbtn.setText("Add")
            val cancel = findViewById<Button>(R.id.cancel_btn)
            cancel.setText("Cancel")
            val name = dialog.findViewById<EditText>(R.id.name)
            val first_name = dialog.findViewById<EditText>(R.id.first_name)
            val email = dialog.findViewById<EditText>(R.id.email)

            confirmbtn.setOnClickListener {
                if (name.text.isEmpty()) {
                    toasMessage(requireContext(), "Please add the name")
                } else {
                    addUser(name.text.trim().toString(),first_name.text.trim().toString(),email.text.trim().toString())

                    dismiss()

                }


            }

            cancel.setOnClickListener {
                dismiss()
            }


        }
            .show()

    }

    //Edit user dialog
    private fun editUserDialog(index: Int) {
        dialog.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(R.layout.add_user_dialog)
            val confirmbtn = findViewById<Button>(R.id.confirm_btn)
            confirmbtn.setText("Update")
            val cancel = findViewById<Button>(R.id.cancel_btn)
            cancel.setText("Cancel")
            val name = dialog.findViewById<EditText>(R.id.name)
            val first_name = dialog.findViewById<EditText>(R.id.first_name)
            val email = dialog.findViewById<EditText>(R.id.email)
            //get Data
            name.setText(usersList[index].user_name.toString())
            first_name.setText(usersList[index].first_name.toString())
            email.setText(usersList[index].user_email.toString())

            confirmbtn.setOnClickListener {
                if (name.text.isEmpty()) {
                    toasMessage(requireContext(), "Please add the name")
                } else {
                    updateUser(usersList[index].id,name.text.trim().toString(),first_name.text.trim().toString(),email.text.trim().toString())

                    dismiss()

                }


            }

            cancel.setOnClickListener {
                dismiss()
            }


        }
            .show()

    }
    //Add user function
private fun addUser(name:String,first_name:String,email:String){
    val data=JSONObject()
    try {
        data.put("user_name",name)
        data.put("first_name",first_name)
        data.put("user_email",email)


    }catch (e:JSONException){
        e.printStackTrace()
    }
    //on Error
    crud.put("users",data,object :OnResponseListener{
        override fun onError(error: String?) {
            toasMessage(requireContext(),"Error")

        }

        //on response
        override fun onResponse(response: String?) {
            //Clear adapter
            isChanged()
            //Toast
            toasMessage(requireContext(),response.toString())
        }

    })




}

    private fun updateUser(id:Int,name:String,first_name:String,email:String){
        val data=JSONObject()
        val conds=JSONObject()
        try {
            //Condition
            conds.put("id",id)

            //Values
            data.put("user_name",name)
            data.put("first_name",first_name)
            data.put("user_email",email)


        }catch (e:JSONException){
            e.printStackTrace()
        }
        //on Error

        crud.set("users",data,conds,object :OnResponseListener{
            override fun onError(error: String?) {
                toasMessage(requireContext(),"Error")

            }

            //on response
            override fun onResponse(response: String?) {
                isChanged()
                toasMessage(requireContext(),response.toString())
            }

        })




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onClick(p0: View?) {
        if (p0!=null){
            when(p0.id){

                R.id.card_user->{
                    editUserDialog(p0.tag as Int)
                }
            }
        }
    }

    private fun isChanged() {
        usersList.clear()
        getUsers()

        userAdapter.notifyDataSetChanged()

    }


    companion object{

        // val url="http://192.168.43.128/api/query/v3/"
        val url="http://192.168.8.101/api/query/v1/"
    }
}