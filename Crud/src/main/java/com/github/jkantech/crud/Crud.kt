package com.github.jkantech.crud

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class Crud (context: Context?,private val url: String) {
    private val queue: RequestQueue = Volley.newRequestQueue(context)
    private val params: HashMap<String, String> = HashMap()



    fun query(sql: String, listener: OnResponseListener?) {
        params["sql"] = sql
        request("$url/sql.php", params, listener)
    }

    private fun request(http: String, params: Map<String, String>, listener: OnResponseListener?) {
        val req = HttpRequest(Request.Method.POST, http, params, { p1 -> listener?.onResponse(p1) }, { p1 ->
            if (listener != null) {
                listener.onError(p1.message)
                p1.printStackTrace()
            }
        })
        queue.add(req)
    }

    /*----------------------GET-----------------------------------*/
    fun getRow(table: String, conds: JSONArray, listener: OnResponseListener?) {
        params["table"] = table
        params["conds"] = conds.toString()
        request("$url/get_row.php", params, listener)

    }

    operator fun get(table: String, conds: JSONObject?, order: JSONObject?, listener: OnResponseListener?) {
        params["table"] = table
        params["conds"] = conds?.toString() ?: JSONObject().toString()
        params["order"] = order?.toString() ?: JSONObject().toString()
        request("$url/get.php", params, listener)
    }

    operator fun get(table: String, conds: JSONObject?, listener: OnResponseListener) {
        get(table, conds, listener, 0)
    }

    private operator fun get(table: String, conds: JSONObject?, listener: OnResponseListener, code: Int) {
        params["table"] = table
        params["conds"] = conds?.toString() ?: JSONObject().toString()
        request("$url/get.php", params, listener)
    }

    operator fun get(url: String, listener: OnResponseListener?) {
        request(url, params, listener)
    }



    /*--------------------------------------------DEL----------------------------------------------*/

    fun del(table: String, conds: JSONObject?, listener: OnResponseListener) {
        params["table"] = table
        params["conds"] = conds?.toString() ?: JSONObject().toString()
        request("$url/del.php", params, listener)
    }


    /*--------------------------------------------SET----------------------------------------------*/
    operator fun set(table: String, vals: JSONObject, conds: JSONObject, listener: OnResponseListener?) {
        params["table"] = table
        params["vals"] = vals.toString()
        params["conds"] = conds.toString()
        request("$url/set.php", params, listener)
    }

    /*-----------------------------------------PUT--------------------------------------*/
    fun put(table: String, params: JSONObject, listener: OnResponseListener?) {
        this.params["table"] = table
        this.params["params"] = params.toString()
        request("$url/put.php", this.params, listener)
    }
    fun put_bulk(table: String, params: JSONArray, listener: OnResponseListener?) {
        this.params["table"] = table
        this.params["params"] = params.toString()
        request("$url/put_bulk", this.params, listener)
    }

    inner class HttpRequest(method: Int, URL: String?, private val params: Map<String, String>, listener: Response.Listener<String?>?, error: Response.ErrorListener?) : StringRequest(method, URL, listener, error) {
        override fun getParams(): Map<String, String> {
            // TODO: Implement this method
            return params
        }

    }




}