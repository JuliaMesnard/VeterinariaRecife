package devandroid.veterinariarecife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import devandroid.vinicius.veterinariarecife.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nome = binding.formNome
        var animal = binding.formAnimal

        dbRef = FirebaseDatabase.getInstance().getReference("Paciente")

        binding.btAgendar.setOnClickListener {

            val fnome = binding.formNome.text.toString()
            val fanimal = binding.formAnimal.text.toString()


            if(fnome.isNotEmpty() && fanimal.isNotEmpty()) {

                val userId = dbRef.push().key!!

                val dadospaciente = dbPaciente(fnome,fanimal)


                dbRef.child(userId).setValue(dadospaciente).addOnCompleteListener{
                    Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_SHORT).show()

                    nome.text.clear()
                    animal.text.clear()

                }.addOnFailureListener{err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                }
            }
            val intent = Intent(this, pacientes::class.java)
            startActivity(intent)
        }
    }
}