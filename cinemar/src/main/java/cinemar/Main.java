package cinemar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.util.ArrayList;

public class Main {
	
	
	public static String Validar_opcion(String regex, String objeto )
	{
		@SuppressWarnings("resource")
		Scanner ingreso= new Scanner (System.in);
		//Validación
				Pattern pt=Pattern.compile(regex);
				Matcher mc= pt.matcher(objeto);
				
				while(!mc.matches())
				{
					System.err.print("Opción incorrecta ingresada. Ingrese una opcion correcta: ") ;
					objeto=ingreso.nextLine();
					mc= pt.matcher(objeto);
				}
		return objeto;
	}	
	

	public static void Zona_clientes()


	{
		Scanner entrada= new Scanner(System.in);
		boolean begin=false;
		boolean registro= false;
			
		System.out.println();
		System.out.println("Usted está por acceder a la zona de clientes.");
		System.out.println("Ingrese el numero de la acción que desea ejecutar.");
		
		System.out.println("1. Registrarse");
		System.out.println("2. Iniciar Sesión");
		
		String navegador = entrada.nextLine();
		navegador= Validar_opcion("[1-2]",navegador);
		
//1.Registrarse
		
		if(navegador.equals("1"))
		{
			System.out.println("Espere un momento...");
			System.out.println();

			registro=FuncionesCliente.Registrarse();
			//si falla regsitro
			while(!registro)
			{
				System.out.println("A. Volver a intentar.");
				System.out.println("B. Salir.");
				navegador=entrada.nextLine();
				navegador= Validar_opcion("[A-B]",navegador);
				
				if(navegador.equals("A"))
				{
					registro = FuncionesCliente.Registrarse();
				}
				
				else if (navegador.equals("B"))
				{
					System.out.println("ADIÓS. Nos vemos en otra ocasión.");
					break;
				}
			}//Cierra while 2
		}//Cierra if
	
//Fin 1. Registro	
		
//Si registro exitoso
		
		if (registro)
		{
			FuncionesCliente.Menu_cl();
		}		
			
		
		
//2. INICIO SESIÓN		
		if (navegador.equals("2"))
		{
			System.out.println("Espere un momento...");
			System.out.println();
			
			begin = FuncionesCliente.Inicio_Sesion();
				
			//si falla inicio
			while(!begin)
			{
				System.out.println("A. Volver a intentar.");
				System.out.println("B. Salir.");
				navegador=entrada.nextLine();
				navegador= Validar_opcion("[A-B]",navegador);
				
				if(navegador.equals("A"))
				{
					begin = FuncionesCliente.Inicio_Sesion();
				}
				
				else if (navegador.equals("B"))
				{
					System.out.println("ADIÓS. Nos vemos en otra ocasión.");
					break;
				}
			}//Cierra while 2
		}//Cierra if

//Fin 2.INICIO SESION	
		
		
		//Si inicio exitoso	
		if (begin)
		{
			FuncionesCliente.Menu_cl();
			
		}
		
		
		
	
		
		entrada.close();
		
}//fin clase
	
	public static void Zona_administrador()
	{
		
		boolean inicio=false;
		@SuppressWarnings("resource")
		Scanner entrada= new Scanner(System.in);
		
		inicio = FuncionesAdmin.Inicio_Sesión();
		
		if(inicio)
		{
			FuncionesAdmin.Menu_adm();
			
		}
	}

	public static void main(String[] args) {
		
//		ArrayList <Cliente> clientes_cinemar = new ArrayList <Cliente>();
//		ArrayList <Usuario> usuarios_cinemar = new ArrayList <Usuario>();
//		ArrayList <Administrador> administradores_cinemar = new ArrayList <Administrador>();

		
		System.out.println("BIENVENIDO A CINEMAR!");
		System.out.print("Ingrese 1 para acceder a la zona de clientes.");
		System.out.print(" En su defecto, ingrese 2 para acceder a la zona de administradores: ");
		
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		String zona = entrada.nextLine();
		
	
		zona= Validar_opcion("[1-2]",zona);

//ZONA DE CLIENTES
		if(zona.equals("1"))
		{

			Zona_clientes();
		}
		
//ZONA DE ADMINISTRADORES
		
		if (zona.equals("2"))
		{
			Zona_administrador();
			

		}
		
		System.out.println();
		


}
}

