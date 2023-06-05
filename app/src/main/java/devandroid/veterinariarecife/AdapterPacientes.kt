package devandroid.veterinariarecife

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterPacientes (private val pacienteList: ArrayList<dbPaciente>):
    RecyclerView.Adapter<AdapterPacientes.ViewHolder>(){

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val PacienteNome : TextView = itemView.findViewById(R.id.cardPaciente)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pacientes_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNome = pacienteList[position]
        holder.PacienteNome.text = currentNome.userNome
    }

    override fun getItemCount(): Int {
        return pacienteList.size
    }
}