package Proyecto;

import java.util.Scanner;

public class Proyecto5 {
    public static String obtenerEstado(int cpu, int ram){

    if(cpu < 70 && ram < 70){
        return "Normal";
    }
    else if((cpu >= 70 && cpu <= 89) ||
            (ram >= 70 && ram <= 89)){
        return "Alerta";
    }
    else{
        return "Critico";
    }
}

    public static void  generalServidores (String[] nom, String[] ips, int[] cpu, int[] ram){
        for(int i=0; i<nom.length;i++){
            String estadoServidor = obtenerEstado(cpu[i], ram[i]);
            System.out.println(nom[i] +
                   " - IP: " + ips[i] +
                   " - CPU: " + cpu[i] +
                   " - RAM: " + ram[i] +
                   " - Estado: " + estadoServidor);
        }
    }
    public static void mayorUso(String[] nombres, int[] cpu, int[] ram){

    int mayorCPU = cpu[0];
    int indiceCPU = 0;

    int mayorRAM = ram[0];
    int indiceRAM = 0;

    for(int i = 1; i < nombres.length; i++){

        if(cpu[i] > mayorCPU){
            mayorCPU = cpu[i];
            indiceCPU = i;
        }

        if(ram[i] > mayorRAM){
            mayorRAM = ram[i];
            indiceRAM = i;
        }
    }

    System.out.println("Servidor con mayor uso de CPU:");
    System.out.println(nombres[indiceCPU] + " - CPU: " + mayorCPU + "%");

    

    System.out.println("Servidor con mayor uso de RAM:");
    System.out.println(nombres[indiceRAM] + " - RAM: " + mayorRAM + "%");
    }
    public static boolean validarIP(String ip){
        return ip.contains(".");
    }

    
    public static void calculoPromedio(int[] cpu, int[] ram){
        double sumaCPU =0;
        double sumaRAM =0;
        for (int i=0; i<cpu.length;i++){
            sumaCPU = sumaCPU + cpu[i];
            sumaRAM = sumaRAM + ram[i];
        }

        double promedioCPU = sumaCPU / cpu.length;
        double promedioRAM = sumaRAM / ram.length;

        System.out.println("Promedio CPU: " + promedioCPU);
        System.out.println("Promedio RAM: " + promedioRAM);


    }
    public static void servidoresCriticos(String[] nombres, int[] cpu, int[] ram){
        System.out.println("Servidores Criticos:");
        for(int i = 0; i < nombres.length; i++){
            String estado = obtenerEstado(cpu[i], ram[i]);
            if(estado.equals("Critico")){
                System.out.println(nombres[i]);
            }
        }
    
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el Numero de Servidores: ");
        int numero_Servidores = scanner.nextInt();
        scanner.nextLine();

        String[] nombres = new String[numero_Servidores];
        String[] ips = new String[numero_Servidores];
        int[] cpu = new int[numero_Servidores];
        int[] ram = new int[numero_Servidores];

        for(int i = 0; i < numero_Servidores; i++){
            System.out.println("Servidor " + (i + 1));
            System.out.print("Nombre: ");
            nombres[i] = scanner.nextLine();
            System.out.print("IP: ");
            ips[i] = scanner.nextLine();
            while(!validarIP(ips[i])){
                System.out.println("IP invalida. Intente nuevamente:");
                ips[i] = scanner.nextLine();
            }
            System.out.print("CPU: ");
            cpu[i] = scanner.nextInt();
            while(cpu[i] < 0 || cpu[i] > 100){
                System.out.println("CPU invalido. Intente nuevamente:");
                cpu[i] = scanner.nextInt();
            }
            
            System.out.print("RAM: ");
            ram[i] = scanner.nextInt();
            while(ram[i] < 0 || ram[i] > 100){
                System.out.println("RAM invalido. Intente nuevamente:");
                ram[i] = scanner.nextInt();
            }
            scanner.nextLine();
        }
        System.out.println("--------------------");
        generalServidores(nombres, ips, cpu, ram);
        calculoPromedio(cpu, ram);
        servidoresCriticos(nombres, cpu, ram);
        mayorUso(nombres, cpu, ram);
        

        scanner.close();
        
    }
    
}
