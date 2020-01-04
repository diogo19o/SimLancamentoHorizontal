import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.*

fun main(args: Array<String>) {

    // vou definir um gráfico com 60 caracteres de largura por 20 caracteres de altura
    val larguraGrafico = 170
    val alturaGrafico = 40
    val grafico = Chart(larguraGrafico, alturaGrafico)

    //............................Enunciado do trabalho....................
    println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~PROGRAMA DA TRAJETÓRIA DO MOVIMENTO OBLIQUO~~~~~~~~~~~~~~~~~~~~~~~~~\n")
    println("Um jogador tem de chutar uma bola de futebol norte-americano de um ponto a 36,0 m do gol.")
    println("Metade do público torce para a bola passar pela barra transversal, que tem altura de 3,05 m.")

    var velocidadeInicial: Double?
    var anguloInicialGraus: Double?
    val forcaGravitica = 10.0

    do {

        //.............................................Qual a velocidade inicial............................................
        println("\nQual é a velocidade inicial com que a bola sai do solo? (m/s)")
        do {
            velocidadeInicial = readLine()!!.toDoubleOrNull()
            if (velocidadeInicial == null) {
                println(">>> Dados de entrada inválidos!")
            }
        } while (velocidadeInicial == null)

        //..............................................Qual o angulo inicial...............................................
        println("Qual o ângulo com a horizontal para a bola passar pela barra transversal?")
        do {
            anguloInicialGraus = readLine()!!.toDoubleOrNull()
            if (anguloInicialGraus == null) {
                println(">>> Dados de entrada inválidos!")
            } else {
                if (anguloInicialGraus !in 0..90) {
                    println(">>> Ângulo inválido!")
                }
            }
        } while (anguloInicialGraus == null || anguloInicialGraus !in 0..90)

        //.....................................................EQUAÇÕES.....................................................

        //............................................angulo inicial em radianos............................................
        val anguloIncialRadianos: Double = anguloInicialGraus * PI / 180

        //......................................valor do Seno e Cosseno do angulo inicial...................................
        val sen: Double = sin(anguloIncialRadianos)
        val cos: Double = cos(anguloIncialRadianos)
        //..................................................Velocidades.....................................................
        val velocidadeInicialX: Double = velocidadeInicial * cos
        val velocidadeInicialY: Double = velocidadeInicial * sen

        //.............................................Tempos...........................................................
        val tempoSubida: Double = velocidadeInicialY / forcaGravitica
        val tempoVoo: Double = 2 * tempoSubida

        //.......................................O alcance maximo da bola...............................................
        val alcanceMaximo: Double = velocidadeInicialX * tempoVoo

        //............................................A altura maxima...................................................
        val alturaMaxima: Double = velocidadeInicialY.pow(2) / (2 * forcaGravitica)

        //................................Sabendo o angulo, qual é a velocidade inicial.................................
        //val dicaVelocidadeInicial = ((-5.0 * 36.0.pow(2)) / ((3.05 - 36.0) * cos.pow(2))).pow(0.5)

        //val dicaVelocidadeInicial = ((5*36.0.pow(2))/(sen * cos)).pow(0.5)

        //val velocidadeMinima = ((2.0 * forcaGravitica  * 36.0)/(sen.pow(2))).pow(0.5)

        //..........................velocidade num ponto.............


        //................................................Trajetoria....................................................


        val xBarra = 36.0
        var yBarra = 0.0
        while (yBarra <= 3.0) {
            grafico.ponto(xBarra, yBarra)
            if(anguloInicialGraus < 45) {
                yBarra += alturaMaxima / 100
            }else{
                yBarra += alturaMaxima / 100000
            }
        }
        var y = 0.0
        var x = 0.0
        var marcarPonto = true
        while (y >= 0.0) {
            y = x * (sen / cos) - ((x.pow(2) * forcaGravitica) / (2 * velocidadeInicialX.pow(2)))
            if (y >= 0) {
                grafico.ponto(x, y)
                x += 0.1
            } else {
                y = -1.0
            }
            when (x) {
                in 36.0..36.1 -> if (y <= 3.05) {
                    marcarPonto = false
                }
            }
        }

        grafico.draw()

        //val dicaVelocidadeInicial = ((-forcaGravitica * (x.pow(2)))/(2 * cos * (y * cos- x * sen))).pow(0.5)
        val dicaVelocidadeInicial = ((-forcaGravitica * (36.0.pow(2)))/(2 * cos * (3.05 * cos - 36 * sen))).pow(0.5)

        if (anguloInicialGraus <= 4.8) {
            println("Então jogador aumenta o ângulo que assim nao chegas lá!")
        }

        println(dicaVelocidadeInicial)
        //println(velocidadeMinima)
        var verCaracteristicasVoo: String? = ""
        var outroChuto: String? = ""
        var verVelocidade: String? = ""
        if (!marcarPonto || alcanceMaximo < 36.0) {
            println("És jogador da NFL ou esta é a tua primeira vez a chutar uma bola?")
            if (velocidadeInicial < dicaVelocidadeInicial) {
                println("Experimenta aumentar a velocidade.")
                println("Queres saber uma velocidade inicial para este angulo de maneira marcar ponto ou vais armar-te em campeão outra vez? (S/N)")
                do {
                    verVelocidade = readLine()
                    if (verVelocidade != null) {
                        if (verVelocidade == "S") {
                            println("Experimenta uma velocidade superior a ${dicaVelocidadeInicial.toInt()} m/s")
                        } else if (verVelocidade != "N") {
                            println(">>> Dados de entrada inválidos!")
                        }
                    } else {
                        println(">>> Dados de entrada inválidos!")
                    }
                } while (verVelocidade == "" || verVelocidade != "S" && verVelocidade != "N")
            }
            if(alcanceMaximo in 30..35){
                println("Estas quase lá falta so mais um pouco!")
            }

        }else{
            println("Uiii, deves ser o melhor jogador da tua zona!")
            println("Mais informações sobre o este movimento? (S/N)")
            do {
                verCaracteristicasVoo = readLine()
                if (verCaracteristicasVoo != null) {
                    if (verCaracteristicasVoo != "S" && verCaracteristicasVoo != "N") {
                        println(">>> Dados de entrada inválidos!")
                    }
                    if(verCaracteristicasVoo == "S"){
                        println("\n Caracteriticas da trajetória:\n")
                        println("-> Altura máxima: $alturaMaxima")
                        println("-> Alcance máximo: $alcanceMaximo")
                        println("-> Tempo de subida: $tempoSubida")
                        println("-> Tempo de voo: $tempoVoo")
                    }
                } else {
                    println(">>> Dados de entrada inválidos!")
                }
            } while (verCaracteristicasVoo == "" || verCaracteristicasVoo != "S" && verCaracteristicasVoo != "N")
            println("\nQueres chutar mais uma vez? (S/N)")
            do {
                outroChuto = readLine()
                if (outroChuto != null) {
                    if (outroChuto != "S" && outroChuto != "N") {
                        println(">>> Dados de entrada inválidos!")
                    }
                } else {
                    println(">>> Dados de entrada inválidos!")
                }
            } while (outroChuto == "" || outroChuto != "S" && outroChuto != "N")
        }

    } while (verVelocidade == "S" || outroChuto != "N")
}