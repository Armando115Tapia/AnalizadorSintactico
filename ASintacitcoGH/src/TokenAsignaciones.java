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
	 private static ArrayList<Token> tablaSimb = new ArrayList();
         private static ArrayList<Token> tablaSimbLoc = new ArrayList();
          private static ArrayList<ContFuncion> tablaFuncion = new ArrayList();
          private static ArrayList<Token> tablaFu = new ArrayList();
          
          /*
          
          public static void InsertarSimbolo(Token identificador, int tipo)
	{
		//En este metodo se agrega a la tabla de tokens el identificador que esta siendo declarado junto con su tipo de dato
		tabla.put(identificador.image, tipo);
	 }
	  */
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
 
        
        public static void induccion_var(Token TokenIzq, Token TokenAsig){
        // 40=int   41=float  42=char  44=bool 43 = string
           // System.out.println("metodo induccion de valores **********************");
            //System.out.println(TokenIzq.kind + "] [ "+TokenAsig.kind);
            int auxIZQ=0;
            int auxASI=0;
            
             for(Token x:tablaSimb)
		{
                if(x.image.equals(TokenIzq.image)){auxIZQ= x.kind;}
                if(x.image.equals(TokenAsig.image)){auxASI= x.kind;}
                }
            
            
            
            if(auxIZQ == 40)
            {
             
                if(auxASI== 41){System.out.println("WARING :"+TokenAsig.image+ " float a  int, en linea = "+TokenAsig.beginLine );}
                if(auxASI == 42){System.out.println("Error semantico no se puede operar entre un int y un char");}
                if(auxASI== 43){System.out.println("Error semantico no se puede operar entre un string y un char");}
                if(auxASI == 44){System.out.println("WARING :"+TokenAsig.image+" bool a  int, en linea = "+TokenAsig.beginLine );}
            }
            
            if(auxIZQ == 41)
            {
                if(auxASI == 40){System.out.println("WARING :"+TokenAsig.image+" int a float, en linea = "+TokenAsig.beginLine );}
                if(auxASI == 42){System.out.println("Error semantico no se puede operar entre un char y float");}
                if(auxASI == 43){System.out.println("Error semantico no se puede operar entre un string y un char");}
                if(auxASI == 44){System.out.println("WARING :"+TokenAsig.image+" bool a float, en linea = "+TokenAsig.beginLine );}
                
                
            }
            
            
            
            if(auxIZQ == 42){if(auxASI != 42){System.out.println("Error semantico: no se puede operar con char");}}
            if(auxIZQ == 43){if(auxASI != 43){System.out.println("Error semantico: no se puede operar con string");}}
          
            if(auxIZQ == 44)
            {
            if(TokenAsig.kind == 40){System.out.println("WARING :"+TokenAsig.image+" int a bool, en linea = "+TokenAsig.beginLine );}
            if(TokenAsig.kind == 42){System.out.println("Error semantico no se puede operar entre un char y bool");}
            if(TokenAsig.kind == 43){System.out.println("Error semantico no se puede operar entre un string y un bool");}
            if(TokenAsig.kind == 41){System.out.println("WARING :"+TokenAsig.image+" float a bool, en linea = "+TokenAsig.beginLine );}
            
            
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

        public static void insertarSimbolo(Token identificador, Token tipo) {
		boolean aux=false;
		for(Token x:tablaSimb)
		{      
                    //System.out.println(x.image + x.kind );
			if(x.image.equals(identificador.image))
			{ aux=true;
			break;}
		}
		if(aux)
		{
		      System.out.println("Error Semantico: El identificador \" " 
                                + identificador.image + "\" ya fue declarado ");
		} else {
			// En este metodo se agrega a la tabla de tokens el identificador que
			// esta siendo declarado junto con su tipo de dato
			//tabla.put(identificador.image, tipo);
			tablaSimb.add(identificador);
		}
	}
        
        
        
        
        
        
         public static void insertarSimboloLocal(Token identificador, int tipo) {
		boolean aux=false;
		for(Token x:tablaSimbLoc)
		{      
                    //System.out.println(x.image + x.kind );
			if(x.image.equals(identificador.image))
			{ aux=true;
			break;}
		}
		if(aux)
		{
		      System.out.println("Error Semantico: El identificador \" " 
                                + identificador.image + "\" ya fue declarado ");
		} else {
			// En este metodo se agrega a la tabla de tokens el identificador que
			// esta siendo declarado junto con su tipo de dato
			//tabla.put(identificador.image, tipo);
			tablaSimbLoc.add(identificador);
		}
	}
        
        
        
        
        
        public static void MostrarTablaSimbolos(){
            String encabezadoVar = "VARIABLE";
            String  encabezadoTipo = "TIPO";
            System.out.println("\n \n");
            System.out.println("[  TABLA   DE   SIMBOLOS GLOBALES ]");
            System.out.printf("| %-10s | %-10s| \n", encabezadoVar,encabezadoTipo );
            System.out.println(" -------------------------");
            for(Token x:tablaSimb)
		{      
                    //Si se cambian los kind por algun motivo,correr solo esta 
                    //linea sin los if de abajo para averiguarlos
               // System.out.printf("| %-15s | %-15s| \n", x.image,x.kind );
                
                if(x.kind == 40){  String strInt="int";  System.out.printf("| %-10s | %-10s| \n", x.image,strInt );}
                if(x.kind == 41){  String strFloat="float";  System.out.printf("| %-10s | %-10s| \n", x.image,strFloat);}
                if(x.kind == 42){  String strCh="char";  System.out.printf("| %-10s | %-10s| \n", x.image,strCh );}
                if(x.kind == 44){  String strbl="bool";  System.out.printf("| %-10s | %-10s| \n", x.image,strbl );}
                if(x.kind == 43){  String strStr="String";  System.out.printf("| %-10s | %-10s| \n", x.image,strStr);}
		}
        }
        
        
        
        public static void MostrarTablaFunciones(){
            String encabezadoVar = "ID";
            String  encabezadoTipo = "TIPO";
            String strParametros = "PARAMETROS ";
            System.out.println("\n \n");
            System.out.println("[  TABLA   DE   FUNCIONES  ]");
            System.out.printf("| %-10s | %-10s| %-10s \n", encabezadoVar,encabezadoTipo,strParametros );
            System.out.println(" -------------------------");
            //System.out.println("la tabla de simboles mide :" +tablaSimbLoc.size());
            for(ContFuncion x:tablaFuncion)
		{      
                    //Si se cambian los kind por algun motivo,correr solo esta 
                    //linea sin los if de abajo para averiguarlos
               // System.out.printf("| %-15s | %-15s| \n", x.image,x.kind );
                
                if(x.getTipoDato() == 40)
                {  String strInt="int";  System.out.printf("| %-10s | %-10s| \n", x.getIdentificador(),strInt );
                }
                
                
                if(x.getTipoDato() == 41)
                {  String strFloat="float";  System.out.printf("| %-10s | %-10s| \n", x.getIdentificador(),strFloat);}
                
                
                if(x.getTipoDato() == 42)
                {  String strCh="char";  System.out.printf("| %-10s | %-10s| \n", x.getIdentificador(),strCh );}
                
                
                if(x.getTipoDato() == 44)
                {String strbl="bool";  System.out.printf("| %-10s | %-10s| \n", x.getIdentificador(),strbl );}
                
                
                if(x.getTipoDato() == 43)
                {  String strStr="String";  System.out.printf("| %-10s | %-10s| \n", x.getIdentificador(),strStr);}
		//System.out.printf(" %-10s | %-10s ",x.getTipoDato(),x.getIdentificador());
                
                
                
                }
        }
        
        
        
        public static void verificarFuncion1(Token identificador) {
		ContFuncion e = new ContFuncion();
		boolean aux=false;
		e.setIdentificador(identificador.image);
                
               // System.out.println(identificador.image +" el kind es " +identificador.kind );
                
		e.setTipoDato(identificador.kind);

                for(ContFuncion x:tablaFuncion)
		{
			if(x.getIdentificador().equals(e.getIdentificador()))
			{ aux=true;
			break;}
		}
		if(aux==false)
		{
			System.out.println("Error Semantico: La funcion \" "+ identificador.image + "\" no ha sido declarada");
		}		
	}
	
	public static void verificarFuncion2(Token funcion, Token variable) {
		int j=-1;
		boolean aux=false;
		ContFuncion nueva = new ContFuncion();
		ArrayList<Token> parametros= new ArrayList();
		for(ContFuncion x: tablaFuncion){
			j++;
			
			if(x.getIdentificador().equals(funcion.image))
			{
				break;
			}
		}
		if(j>=0)
		{
			nueva=tablaFuncion.get(j);
			parametros=nueva.getParametros();
			for(Token y: parametros)
			{
				if(y.image.equals(variable.image) && (y.kind==variable.kind))
				{
					aux=true;
				} 
				aux=false;
			}
		
		if(aux==false)
		{
			System.out.println("Error Semantico: La funcion \" "+ funcion.image + "\" parametros incorrectos");
		}	
		}else
		{
			System.out.println("Error Semantico: La funcion \" "+ funcion.image + "\" no ha sido declarada");

		}
	}
        
        public static void insertarFuncion(Token identificador) {
		ContFuncion e = new ContFuncion();
		e.setIdentificador(identificador.image);
		e.setTipoDato(identificador.kind);
                boolean blaux = false;
                //comprobar si la funcion no ha sido declarada antes
                 for(ContFuncion x:tablaFuncion){
                 
                     if(x.getIdentificador().equals(e.getIdentificador())){
                     blaux = true;}
                     
                     }
                if(blaux){System.out.println("Error Semantico la funcion " +e.getIdentificador() 
                        +" ya fue declarada " );}
                else{tablaFuncion.add(e);}
                
                
                
                
		
	}
	
	public static void insertarParametros(Token funcion, Token variable)
	{ int j=-1;
	ContFuncion nueva = new ContFuncion();
	for(ContFuncion x: tablaFuncion){
		j++;
		if(x.getIdentificador().equals(funcion.image))
		{
			break;
		}
	}
	if(j>=0)
	{
		nueva=tablaFuncion.get(j);
		nueva.setParametros(variable);
	}
		
	}
        
 }
  
  
  
