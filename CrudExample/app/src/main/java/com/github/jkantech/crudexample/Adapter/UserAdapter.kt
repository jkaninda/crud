package com.github.jkantech.crudexample.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.github.jkantech.crudexample.R
import com.github.jkantech.crudexample.models.Users

class UserAdapter(val context: Context, val users:Users, val itemClickListener: View.OnClickListener):RecyclerView.Adapter<UserAdapter.ViewHolder>() {



    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val cardView=itemView.findViewById<CardView>(R.id.card_user)
        val name=cardView.findViewById<TextView>(R.id.name)
        val fist_name=cardView.findViewById<TextView>(R.id.fist_name)
        val email=cardView.findViewById<TextView>(R.id.email)





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)

        return  ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user=users[position]
        holder.cardView.tag=position
        holder.name.text=user.user_name
        holder.fist_name.text=user.first_name
        holder.email.text=user.user_email
        holder.cardView.setOnClickListener(itemClickListener)

    }

    override fun getItemCount(): Int {
        return users.size
    }
}