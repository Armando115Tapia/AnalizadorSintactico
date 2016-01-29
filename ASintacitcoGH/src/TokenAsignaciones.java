import java.io.PrintStream;
import java.util.Hashtable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Enumeration;
class TokenAsignaciones
{
	  //Variable para validar asignaciones a caracteres(ichr)
	  public static int segunda = 0;
	  //Tabla que almacenara los tokens declarados
	  private static Hashtable tabla = new Hashtable();
	  
	  //Listas que guardaran los tipos compatibles de las variables
	  private static ArrayList<Integer> intComp = new ArrayList();
	  private static ArrayList<Integer> decComp = new ArrayList();
	  private static ArrayList<Integer> strComp = new ArrayList();
	  private static ArrayList<Integer> chrComp = new ArrayList();
	  private static ArrayList<Integer> boolComp = new ArrayList();
	 public static void InsertarSimbolo(Token identificador, int tipo)
	{
		//En este metodo se agrega a la tabla de tokens el identificador que esta siendo declarado junto con su tipo de dato
		tabla.put(identificador.image, tipo);
	 }
	  
	public static void SetTables()
	{
		//Compatibles con int 
		intComp.add(41); //int
		intComp.add(43); //float
		intComp.add(46); //bool
		intComp.add(47); 
		intComp.add(48); 
		intComp.add(53); 
		
		//Compatibles con float 
		
		decComp.add(41); //int
		decComp.add(43); //float
		decComp.add(46); //bool
		decComp.add(47); 
		decComp.add(49); 
		decComp.add(53); 
		
		//Compatibles con char = char
		chrComp.add(42); //char
		chrComp.add(51); //char
			
		//Compatibles con string = string
		strComp.add(43); //string
		strComp.add(50); //string
		
		//Compatibles con bool 
		boolComp.add(46); //bool
		boolComp.add(41); //int
		boolComp.add(43); //float
		boolComp.add(53); 
		boolComp.add(47); 
		boolComp.add(49); 
	}
 
	public static String checkAsing(Token TokenIzq, Token TokenAsig)
	{
	//variables en las cuales se almacenara el tipo de dato del identificador y de las asignaciones (ejemplo: n1(tipoIdent1) = 2(tipoIdent2) + 3(tipoIdent2))
		int tipoIdent1;
		int tipoIdent2;	
                if(TokenIzq.kind != 53)		
		{
			try 
			{
				//Si el TokenIzq.image existe dentro de la tabla de tokens, entonces tipoIdent1 toma el tipo de dato con el que TokenIzq.image fue declarado
				tipoIdent1 = (Integer)tabla.get(TokenIzq.image);	
			}
			catch(Exception e)
			{
				//Si TokenIzq.image no se encuentra en la tabla en la cual se agregan los tokens, el token no ha sido declarado, y se manda un error
				return "Error Semantico : El identificador " + TokenIzq.image + " No ha sido declarado \r\nLinea: " + TokenIzq.beginLine;
			}}else
		if(TokenIzq.kind != 47 && TokenIzq.kind != 49)	//comprobacion de entero o flotante	
		{
			try 
			{
				//Si el TokenIzq.image existe dentro de la tabla de tokens, entonces tipoIdent1 toma el tipo de dato con el que TokenIzq.image fue declarado
				tipoIdent1 = (Integer)tabla.get(TokenIzq.image);	
			}
			catch(Exception e)
			{
				//Si TokenIzq.image no se encuentra en la tabla en la cual se agregan los tokens, el token no ha sido declarado, y se manda un error
				return "Error Semantico : El identificador " + TokenIzq.image + " No ha sido declarado \r\nLinea: " + TokenIzq.beginLine;
			}
		}
		else 		
			tipoIdent1 = 0;
			
		//integer
		if(TokenAsig.kind == 48)	
		{
			/*Si el tipo de dato que se esta asignando, es algun identificador(kind == 49) 
			se obtiene su tipo de la tabla de tokens para poder hacer las comparaciones*/
			try
			{
				tipoIdent2 = (Integer)tabla.get(TokenAsig.image);
			}
			catch(Exception e)
			{
				//si el identificador no existe manda el error
				return "Error Semantico : El identificador " + TokenAsig.image + " No ha sido declarado \r\nLinea: " + TokenIzq.beginLine;
			}
		}
				//Si el dato es entero,decimal,flotante,bool
		else if(TokenAsig.kind == 47 || TokenAsig.kind == 49 || TokenAsig.kind == 50 || TokenAsig.kind == 51 || TokenAsig.kind == 53)
			tipoIdent2 = TokenAsig.kind;//tipoIdent2 = tipo_del_dato
		else //Si no, se inicializa en algun valor "sin significado(con respecto a los tokens)", para que la variable este inicializada y no marque error al comparar
			tipoIdent2 = 0; 

			
		if(tipoIdent1 == 41) //Int
		{
			//Si la lista de enteros(intComp) contiene el valor de tipoIdent2, entonces es compatible y se puede hacer la asignacion
			if(intComp.contains(tipoIdent2))
                            
				return " ";
			else //Si el tipo de dato no es compatible manda el error
				return "Error Semantico : No se puede convertir " + TokenAsig.image + " a Entero \r\nLinea: " + TokenIzq.beginLine;
		}
		else if(tipoIdent1 == 43) //float
		{
			if(decComp.contains(tipoIdent2))
				return " ";
			else
				return "Error Semantico : No se puede convertir " + TokenAsig.image + " a Decimal \r\nLinea: " + TokenIzq.beginLine;
		}
		else if(tipoIdent1 == 44) //char
		{
			/*variable segunda: cuenta cuantos datos se van a asignar al caracter: 
				si a el caracter se le asigna mas de un dato (ej: 'a' + 'b') marca error 
				NOTA: no se utiliza un booleano ya que entraria en asignaciones pares o impares*/
			segunda++;
			if(segunda < 2)
			{
				if(chrComp.contains(tipoIdent2))
					return " ";				
				else
					return "Error Semantico : No se puede convertir " + TokenAsig.image + " a Caracter \r\nLinea: " + TokenIzq.beginLine;	
			}
			else 		
				return "Error: No se puede asignar mas de un valor a un caracter \r\nLinea: " + TokenIzq.beginLine;
			
		}
		else if(tipoIdent1 == 45) //string
		{
			if(strComp.contains(tipoIdent2))
				return " ";
			else
				return "Error Semantico : No se puede convertir " + TokenAsig.image + " a Cadena \r\nLinea: " + TokenIzq.beginLine;
		}
		else if(tipoIdent1 == 46) //bool
		{
			if(boolComp.contains(tipoIdent2))
				return " ";
			else
				return "Error Semantico : No se puede convertir " + TokenAsig.image + " a Booleano \r\nLinea: " + TokenIzq.beginLine;
		}
		else
		{
			return "El Identificador " + TokenIzq.image + " no ha sido declarado" + " Linea: " + TokenIzq.beginLine;
		}
             
	}	  
	
	 
	public static String checkVariable(Token checkTok)
	{
		try
		{
			//Intenta obtener el token a verificar(checkTok) de la tabla de los tokens
			int tipoIdent1 = (Integer)tabla.get(checkTok.image);
			return " ";
		}
		catch(Exception e)
		{
			//Si no lo puede obtener, manda el error
			return "Error: El identificador " + checkTok.image + " No ha sido declarado \r\nLinea: " + checkTok.beginLine;
		}
	}
	
	public static void visualizarTablas()
	{
		Enumeration e = tabla.keys();
		Object obj;
		System.out.printf( "TABLA DE SIMBOLOS\n"+"IDENTI"+"    |  "+"TIPO\n");
		
		while(e.hasMoreElements())
		{
			obj=e.nextElement();
			System.out.printf("%6s%5s%6s",obj,":",tipovariable(obj)+"\n");
		}
	}
	
	static String tipovariable(Object o )
	{
		String nombre="";
		if(tabla.get(o).equals(41))
		{
			nombre="Int";
		}
		if(tabla.get(o).equals(43))
		{
			nombre="Float";
		}
		if(tabla.get(o).equals(44))
		{
			nombre="Char";
		}
		if(tabla.get(o).equals(45))
		{
			nombre="String";
		}
		if(tabla.get(o).equals(46))
		{
			nombre="Bool";
		}
               	return nombre;
	}

 }
  
  
  
