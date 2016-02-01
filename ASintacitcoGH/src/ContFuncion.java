
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class ContFuncion {
    
       private ArrayList<Token> variables = new ArrayList();
       private ArrayList<Token> parametros = new ArrayList();
       private int tipoDato;
	private String identificador;
	
	

	public int getTipoDato() {
		return tipoDato;
	}


	public void setTipoDato(int tipoDato) {
		this.tipoDato = tipoDato;
	}


	public String getIdentificador() {
		return identificador;
	}


	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}


	public ArrayList<Token> getParametros() {
		return parametros;
	}


	public void setParametros(Token parametros) {
		this.parametros.add(parametros);
	}
	public ArrayList<Token> getVariables() {
		return parametros;
	}


	public void setVariables(Token parametros) {
		this.variables.add(parametros);
	}
	
	

	public String toString() {
		String salida="\n";
		salida+=tipos(tipoDato)+" : "+identificador+" ( "+salidaParametros()+" )"+" { "+salidaDeclaraciones()+" }";
		return salida;
	}


	public String salidaParametros() {
		String salida="";
		
		for(Token x:parametros)
		{
			salida+=x.image+":"+tipos(x.kind)+" ";
		}
		return salida;
	}
	
	public String salidaDeclaraciones() {
		String salida="";
		
		for(Token x:variables)
		{
			salida+=x.image+":"+tipos(x.kind);
		}
		return salida;
	}
	static String tipos(int o) {
		String nombre = "";
		if (o == 40) {
			nombre = "Int";
		}
		if (o == 41) {
			nombre = "Float";
		}
		if (o == 42) {
			nombre = "Char";
		}
		if (o == 43) {
			nombre = "String";
		}
		 if (o == 44) {
			nombre = "Bool";
		}
		return nombre;

	}
    
 

   
}
