package devandroid.veterinariarecife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class pacientes : AppCompatActivity() {

    private lateinit var pacienteRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var pacienteList: ArrayList<dbPaciente>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacientes)

        pacienteRecyclerView = findViewById(R.id.listPacientes)
        pacienteRecyclerView .layoutManager = LinearLayoutManager(this)
        pacienteRecyclerView .setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        pacienteList = arrayListOf<dbPaciente>()

        getPacientesData()
    }
    private fun getPacientesData() {
        pacienteRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Paciente")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                pacienteList.clear()
                if(snapshot.exists()) {
                    for(empSnap in snapshot.children) {
                        val dData =empSnap.getValue(dbPaciente::class.java)
                        pacienteList.add(dData!!)
                    }
                    val vAdapter = AdapterPacientes(pacienteList)
                    pacienteRecyclerView.adapter = vAdapter

                    pacienteRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}