
/* --------------------------Codigo de Usuario----------------------- */
package ejemplocup;
import java_cup.runtime.*;
import java.io.Reader;
      
%%
%class AnalizadorLexico
%line
%column
%cup
   
%{
    /*  Generamos un java_cup.Symbol para guardar el tipo de token 
        encontrado */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    /* Generamos un Symbol para el tipo de token encontrado 
       junto con su valor */
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}
   
EXP_ALPHA=[A-Za-z]
EXP_DIGITO=[0-9] 
CERO=00
EXP_ALPHANUMERIC={EXP_ALPHA}|{EXP_DIGITO}
IDENTIFICADOR={EXP_ALPHA}({EXP_ALPHANUMERIC})*

INTEGERR= "-"?({EXP_DIGITO})+
INTEGERP={EXP_DIGITO}+
NUMFLOAT= "-"?{EXP_DIGITO}*"."{EXP_DIGITO}+ 




Salto = \r|\n|\r\n
Espacio     = {Salto} | [ \t\f]

//ENTERO = 0 | [1-9][0-9]*



%% 
   

   
<YYINITIAL> {
   
    /* Regresa que el token SEMI declarado en la clase sym fue encontrado. */
    ";"                { System.out.print(" ; \n ");
	                     return symbol(sym.SEMI); }
    /* Regresa que el token OP_SUMA declarado en la clase sym fue encontrado. */
    "+"                {  System.out.print(" + ");
                          return symbol(sym.OP_SUMA); }
    /* Regresa que el token OP_SUMA declarado en la clase sym fue encontrado. */
    "-"                {  System.out.print(" - ");
                          return symbol(sym.OP_RESTA); }
    /* Regresa que el token OP_SUMA declarado en la clase sym fue encontrado. */
    "*"                {  System.out.print(" * ");
                          return symbol(sym.OP_MULT); }
    /* Regresa que el token PARENIZQ declarado en la clase sym fue encontrado. */
    "("                {  System.out.print(" ( ");
                          return symbol(sym.PARENIZQ); }
                          /* Regresa que el token PARENIZQ declarado en la clase sym fue encontrado. */
    ")"                {  System.out.print(" ) ");
                          return symbol(sym.PARENDER); }
						  
	//--------------------------------------------------------------------------------
	":"                {  System.out.print(" : ");
                          return symbol(sym.DOSPUNTOS); }
						  
	"++"                {  System.out.print(" ++ ");
                          return symbol(sym.INCREMENTO); }					  
						  
						  
   "="                {  System.out.print(" IGUAL ");
                          return symbol(sym.IGUAL); }
						  
   "["                {  System.out.print(" [ ");
                          return symbol(sym.corchIZQ); }
						  
   "]"                {  System.out.print(" ] ");
                          return symbol(sym.corchDER); }
						  
  "{"                {  System.out.print(" { ");
                          return symbol(sym.llaveIZQ); }
						  
   "}"                {  System.out.print(" } ");
                          return symbol(sym.llaveDER); }
						  
	","                {  System.out.print(" , ");
                          return symbol(sym.COMMAA); }
//Declaraciones de Operadores compuestos 
">="                {  System.out.print(" >= ");
                          return symbol(sym.MAY_IGUAL); }
						  
"<="                {  System.out.print(" <= ");
                          return symbol(sym.MENOR_IGUAL); }

"=="                {  System.out.print(" == ");
                          return symbol(sym.IGUAL_IGUAL); }

"!="                {  System.out.print(" != ");
                          return symbol(sym.DIFERENT); }

"&&"                {  System.out.print(" && ");
                          return symbol(sym.AND_AND); }

"||"                {  System.out.print(" || ");
                          return symbol(sym.OR); }						  
						  
						  						  						  
						    
   "INT"                {  System.out.print(" INT ");
                          return symbol(sym.INT); }
						  
  "FLOAT"                {  System.out.print(" FLOAT ");
                          return symbol(sym.FLOAT); }

 "CHAR"                {  System.out.print(" CHAR ");
                          return symbol(sym.CHAR ); }
						  
 "BOOL "                {  System.out.print(" BOOL ");
                          return symbol(sym.BOOL ); }
						  
 "STRING"                {  System.out.print(" STRING ");
                          return symbol(sym.STRING); }
						  
 "IF"                {  System.out.print(" IF ");
                          return symbol(sym.IF); }
						  
 "THEN"                {  System.out.print(" THEN ");
                          return symbol(sym.THEN); }
						  
 "ELSE"                {  System.out.print(" ELSE ");
                          return symbol(sym.ELSE); }

"WHILE"                {  System.out.print(" WHILE ");
                          return symbol(sym.WHILE); }

"DO"                {  System.out.print(" DO ");
                          return symbol(sym.DO); }						  
   
    /* Si se encuentra un entero, se imprime, se regresa un token numero
        que representa un entero y el valor que se obtuvo de la cadena yytext
        al convertirla a entero. yytext es el token encontrado. */
		
	{CERO}      {   System.out.print(yytext()); 
                      return symbol(sym.CERO); }
		
		/*
    {ENTERO}      {   System.out.print(yytext()); 
                      return symbol(sym.ENTERO, new Integer(yytext())); }*/
					  
					  
	{IDENTIFICADOR}      {   System.out.print(yytext()); 
                      return symbol(sym.IDENTIFICADOR, new String (yytext())); }

					  
					  
	{INTEGERP}      {   System.out.print(yytext()); 
                      return symbol(sym.INTEGERP, new Integer (yytext())); }
					  
					
{INTEGERR}      {   System.out.print(yytext()); 
                      return symbol(sym.INTEGERR, new Integer (yytext())); }					
					  
   {NUMFLOAT}      {   System.out.print(yytext()); 
                      return symbol(sym.NUMFLOAT, new Float (yytext())); }

    /* No hace nada si encuentra el espacio en blanco */
    {Espacio}       { /* ignora el espacio */ } 
	
	
	
	
	
	
	
	
	
}


/* Si el token contenido en la entrada no coincide con ninguna regla
    entonces se marca un token ilegal */
[^]                    { throw new Error("Caracter ilegal <"+yytext()+">"); }
