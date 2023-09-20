package exercicis

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


fun main(args: Array<String>){
    val f = File("Penyagolosa.bmp")

    val fi = FitxerImatge(f)
    fi.transformaNegatiu()
    fi.transformaObscur()
    fi.transformaBlancNegre()
}


class FitxerImatge(fEnt: File) {
    var f: File = File("")   // No modifiqueu aquesta línia. El seu valor s'ha de modificar en el constructor

    init {
         //Constructor
        // Control d'existència del fitxer i control de l'extensió .bmp (traure missatges d'error)
        // En cas que tot siga correcte, inicialitzar f amb fEnt

        if(fEnt.exists() && fEnt.extension== "bmp"){
            f = fEnt
        }else{
            if(!fEnt.exists()) {
                println("Error: No existe el archivo")
            }else{
                println("Error: La extension no es correcta ")
            }
        }

    }

    fun transformaNegatiu() {
        // Transformar a negatiiu i guardar en _n.bmp
        val f2 = File(f.name.replace(".bmp","_n.bmp"))
        var input = FileInputStream(f)
        var out = FileOutputStream(f2)
        var readByte = input.read()
        var count = 0
        while(readByte != -1){
            if(count <= 54){
                out.write(readByte)
            }else{
                out.write(255-readByte)
            }
            readByte =input.read()
            count++
        }

    }

    fun transformaObscur() {
        // Transformar a una imatge més fosca i guardar en _o.bmp
        val f2 = File(f.name.replace(".bmp","_o.bmp"))
        var input = FileInputStream(f)
        var out = FileOutputStream(f2)
        var readByte = input.read()
        var count = 0
        while(readByte != -1){
            if(count <= 54){
                out.write(readByte)
            }else{
                out.write(readByte / 2)
            }
            readByte =input.read()
            count++
        }
    }


    fun transformaBlancNegre() {
        // Transformar a una imatge en blanc i negre i guardar en _bn.bmp
        val f2 = File(f.name.replace(".bmp","_bn.bmp"))
        var input = FileInputStream(f)
        var out = FileOutputStream(f2)
        var readByte = input.read()
        var count = 0
        while(readByte != -1){
            if(count <= 54){
                out.write(readByte)
            }else{
                var r = readByte
                var g= input.read()
                var b = input.read()

                var gris = (r + g +b ) /3

                out.write(gris)
                out.write(gris)
                out.write(gris)
            }
            readByte =input.read()
            count++
        }
        input.close()
        out.close()
    }

}

