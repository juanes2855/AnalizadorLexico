/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quindío (Armenia - Colombia)
 * Programa de Ingeniería de Sistemas y Computación
 *
 * Asignatura: Teoría de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Diseño original por: Leonardo A. Hernández R. - Agosto 2008 - Marzo 2009
 * Modificado y usado por: Claudia E. Quiceno R- Julio 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

import java.util.ArrayList;


/**
 * Clase que modela un analizador léxico
 */

public class AnalizadorLexico {
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Extrae los tokens de un código fuente dado
     * @param cod - código al cual se le van a extraer los tokens - !=null
     * @return vector con los tokens
     */
    public ArrayList extraerTokens( String cod )
    {
    	Token token;
    	ArrayList vectorTokens = new ArrayList();

	    // El primer token se extrae a partir de posición cero
    	int i = 0;

    	// Ciclo para extraer todos los tokens
    	while( i < cod.length() )
		{
	        // Extrae el token de la posición i
			token = extraerSiguienteToken( cod, i);
	        vectorTokens.add( token );
	        i = token.darIndiceSiguiente();
    	}
		return vectorTokens;
    }

    /**
     * Extrae el token de la cadena cod a partir de la posición i, basándose en el Autómata
     * @param cod - código al cual se le va a extraer un token - codigo!=null
     * @param i - posición a partir de la cual se va a extraer el token  - i>=0
     * @return token que se extrajo de la cadena
     */
    public Token extraerSiguienteToken( String cod, int i )
    {
    	Token token;

		// Intenta extraer un entero
		token = extraerEntero( cod, i);
		if ( token != null )
			return token;
    	
		//Intenta extraer un real
		token = extraerReal(cod, i);
		if( token != null)
			return token;
		
    	// Intenta extraer un operador aritmetico
		token = extraerOperadorAritmetico(cod, i);
		if ( token != null )
			return token;

		// Intenta extraer un operador de asignación
		token = extraerOperadorAsignacion( cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer un operador de logico
		token = extraerOperadorLogico( cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer un operador de relacional
		token = extraerOperadorRelacional( cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer un operador de relacional
		token = extraerSimboloApertura(cod, i);
		if ( token != null )
			return token;
				
		// Intenta extraer un operador de relacional
		token = extraerSimboloCierre(cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer un separador de sentencia
		token = extraerSeparadorSentencia(cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer una palabra reservada sin categoria
		token = extraerPalabraReservadaSinCategoria(cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer una palabra reservada ciclo
		token = extraerPalabraReservadaCiclo(cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer una palabra reservada desicion
		token = extraerPalabraReservadaDesicion(cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer una palabra reservada clase
		token = extraerPalabraReservadaClase(cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer una palabra reservada tipo de datos
		token = extraerPalabraReservadaTipoDatos(cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer una palabra reservada tipo de datos
		token = extraerPalabraReservadaNombreVariable(cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer una palabra reservada tipo de datos
		token = extraerPalabraReservadaNombreClase(cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer una palabra reservada tipo de datos
		token = extraerPalabraReservadaNombreMetodo(cod, i);
		if ( token != null )
			return token;
		
		// Intenta extraer un identificador
		token = extraerIdentificador( cod, i);
		if ( token != null )
			return token;
			
		// Extrae un token no reconocido
		token = extraerNoReconocido( cod, i);
		return token;
    }

    /**
     * Intenta extraer un entero de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un entero - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un entero  - 0<=indice<codigo.length()
     * @return el token entero o NULL, si el token en la posición dada no es un entero. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	
    // Este método usa el método substring(), que se explica a continuación:
    // x.substring( i, j ) retorna una nueva cadena que es una subcadena de la cadena x.
    // La subcadena comienza en la posición i y
    // se extiende hasta el carácter en la posición j-1.
    // Ejemplo: "universidad".substring(3,6) retorna "ver",
	
	
	public Token extraerEntero ( String cod, int i)
	{
		
		int j;
		String lex;
		if( cod.charAt(i)=='E' ){
			j=i+1;
			if( j<cod.length() && esDigito(cod.charAt(j)) ){		
			    do
			    	j++;
			    while (  j<cod.length( ) && esDigito(cod.charAt(j)) );
		        lex =  cod.substring( i, j);			    
				Token token = new Token( lex, Token.ENTERO, j );
				return token;			
			}
		}
		
		return null;
	}

	 /**
     * Intenta extraer un real de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un entero - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un entero  - 0<=indice<codigo.length()
     * @return el token entero o NULL, si el token en la posición dada no es un entero. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	
	public Token extraerReal (String cod, int i)
	{
		String palabra= cod.substring(i);
		int j, indice =0;
		String lex;
		if( palabra.charAt(indice)=='R' ){
			j=i+1;
			if( j<cod.length()){
				int aux = palabra.indexOf('.');
				if((aux+1)< palabra.length()){
					if(palabra.contains(".") && esDigito(cod.charAt(j)) && esDigito(palabra.charAt(aux+1)))
					{
					    do
					    	j++;
					    while (j<cod.length( ) && esDigito(cod.charAt(j)));
				        lex =  cod.substring( i, j+1);		
				        if(palabra.charAt(aux)=='.'){
				        	do{
				        		j++;
				        	}
				        	while ( j<cod.length( ) && esDigito(cod.charAt(j)));
				        	lex = cod.substring( i, j);		
				        	Token token = new Token( lex, Token.REAL, j);
							return token;
				        }	
					}		
				}
			}
		}
		
		return null;
	}
    /**
     * Intenta extraer un operador aditivo de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer el operador aditivo  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer el operador aditivo  - 0<=i<codigo.length()
     * @return el token operador aditivo o NULL, si el token en la posición dada no es un operador aditivo.El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerOperadorAritmetico ( String cod, int i )
	{
			if( cod.charAt(i) =='s'){
				int j=i+1;
				if( j<cod.length() && (cod.charAt(j) =='u')){		
					j++;
					if( j<cod.length() && (cod.charAt(j) =='m' ||  cod.charAt(j) =='b' )){		
						j++;
				        String lex =  cod.substring( i, j);			    
						Token token = new Token( lex, Token.OPERADORARITMETICO, j );
						return token;
					}
				}
			}
			if( cod.charAt(i) =='m'){
				int j=i+1;
				if( j<cod.length() && (cod.charAt(j) =='u')){		
					j++;
					if( j<cod.length() && cod.charAt(j) =='l'){		
						j++;
				        String lex =  cod.substring( i, j);			    
						Token token = new Token( lex, Token.OPERADORARITMETICO, j );
						return token;
					}
				}
				if( j<cod.length() && (cod.charAt(j) =='o')){		
					j++;
					if( j<cod.length() && cod.charAt(j) =='d'){		
						j++;
				        String lex =  cod.substring( i, j);			    
						Token token = new Token( lex, Token.OPERADORARITMETICO, j );
						return token;
					}
				}
			}
			
		return null;
	}

    /**
     * Intenta extraer un operador de asignación de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer el operador de asignación  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer el operador de asingación  - 0<=i<codigo.length()
     * @return el token operador asignación o NULL, si el token en la posición dada no es un operador de asignación. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerOperadorAsignacion ( String cod, int i )
	{
		if( cod.charAt(i) =='-'){
			int j=i+1;
			if( j<cod.length() && (cod.charAt(j) =='e')){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='v' ){		
					j++;
			        String lex =  cod.substring( i, j);			    
					Token token = new Token( lex, Token.OPERADORASIGNACION, j );
					return token;
				}
			}
		}
		return null;
	}
	
	/**
     * Intenta extraer un operador lofico a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer el operador logico  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer el operador logico - 0<=i<codigo.length()
     * @return el token operador logico o NULL, si el token en la posición dada no es un operador logico. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerOperadorLogico( String cod, int i )
	{
		if( cod.charAt(i) =='a'){
			int j=i+1;
			if( j<cod.length() && (cod.charAt(j) =='n')){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='d' ){		
					j++;
			        String lex =  cod.substring( i, j);			    
					Token token = new Token( lex, Token.OPERADORLOGICO, j );
					return token;
				}
			}
		}
		if( cod.charAt(i) =='o'){
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='r' ){		
				j++;
			    String lex =  cod.substring( i, j);			    
				Token token = new Token( lex, Token.OPERADORLOGICO, j );
				return token;
			}
		}
		if( cod.charAt(i) =='n'){
			int j=i+1;
			if( j<cod.length() && (cod.charAt(j) =='o')){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='t' ){		
					j++;
			        String lex =  cod.substring( i, j);			    
					Token token = new Token( lex, Token.OPERADORLOGICO, j );
					return token;
				}
			}
		}
		return null;
	}
	
	/**
     * Intenta extraer un operador relacional a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer el operador relacional  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer el operador relacional - 0<=i<codigo.length()
     * @return el token operador relacional o NULL, si el token en la posición dada no es un operador logico. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerOperadorRelacional( String cod, int i )
	{
		if( cod.charAt(i) =='-'){
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='l'){		
				j++;
				if( j<cod.length() && (cod.charAt(j) =='t' || cod.charAt(j) =='e')){		
					j++;
			        String lex =  cod.substring( i, j);			    
					Token token = new Token( lex, Token.OPERADORRELACIONAL, j );
					return token;
				}
			}
			if( j<cod.length() && cod.charAt(j) =='h'){		
				j++;
				if( j<cod.length() && (cod.charAt(j) =='t' || cod.charAt(j) =='e')){		
					j++;
			        String lex =  cod.substring( i, j);			    
					Token token = new Token( lex, Token.OPERADORRELACIONAL, j );
					return token;
				}
			}
			if( j<cod.length() && cod.charAt(j) =='e'){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='q'){		
					j++;
			        String lex =  cod.substring( i, j);			    
					Token token = new Token( lex, Token.OPERADORRELACIONAL, j );
					return token;
				}
			}
		}
		return null;
	}
	
	/**
     * Intenta extraer un simbolo de apertura de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer el simbolo de apertura  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer el simbolo de apertura  - 0<=i<codigo.length()
     * @return el token simbolo de apertura o NULL, si el token en la posición dada no es un simbolo de apertura. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerSimboloApertura( String cod, int i )
	{
		if( cod.charAt(i) =='<'){
			int j=i+1;
			if( j<cod.length() && (cod.charAt(j) =='%' || cod.charAt(j) =='a')){		
				j++;
			    String lex =  cod.substring( i, j);			    
				Token token = new Token( lex, Token.SIMBOLOAPERTURA, j );
				return token;
			}
		}
		return null;
	}
	
	/**
     * Intenta extraer un simbolo de cierra de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer el simbolo de cierre  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer el simbolo de ciere  - 0<=i<codigo.length()
     * @return el token simbolo de cierre o NULL, si el token en la posición dada no es un simbolo de cierre. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerSimboloCierre( String cod, int i )
	{
		if( (cod.charAt(i) =='%' || cod.charAt(i) =='a')){
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='>'){		
				j++;
			    String lex =  cod.substring( i, j);			    
				Token token = new Token( lex, Token.SIMBOLOCIERRE, j );
				return token;
			}
		}
		return null;
	} 
	
	/**
     * Intenta extraer un separador de sentencia de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer el separador de sentencia  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer el separador de sentencia   - 0<=i<codigo.length()
     * @return el token separador de sentencia  o NULL, si el token en la posición dada no es un separador de sentencia . El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerSeparadorSentencia( String cod, int i )
	{
		if(cod.charAt(i) =='|'){
			int j=i+1;
			String lex =  cod.substring( i, j);			    
			Token token = new Token( lex, Token.SEPARADORSENTENCIA, j );
			return token;
		}
		if(cod.charAt(i) ==':'){
			int j=i+1;
			String lex =  cod.substring( i, j);			    
			Token token = new Token( lex, Token.SEPARADORSENTENCIA, j );
			return token;
		}
		if(cod.charAt(i) =='¬'){
			int j=i+1;
			String lex =  cod.substring( i, j);			    
			Token token = new Token( lex, Token.SEPARADORSENTENCIA, j );
			return token;
		}
		return null;
	} 
	
	/**
     * Intenta extraer una palabra reservada para un  ciclo a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer una palabra reservada para un  ciclo  - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer una palabra reservada para un  ciclo - 0<=i<codigo.length()
     * @return el token una palabra reservada para un  ciclo o NULL, si el token en la posición dada no es una palabra reservada para un  ciclo. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerPalabraReservadaCiclo( String cod, int i )
	{
		if( cod.charAt(i) =='L'){
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='o'){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='o'){		
					j++;
					if( j<cod.length() && cod.charAt(j) =='p'){		
						j++;
						String lex =  cod.substring( i, j);			    
						Token token = new Token( lex, Token.PALABRARESEVADACICLO, j );
						return token;
					}
				}
			}
		}
		if( cod.charAt(i) =='C'){
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='i'){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='c'){		
					j++;
					if( j<cod.length() && cod.charAt(j) =='l'){		
						j++;
						if( j<cod.length() && cod.charAt(j) =='o'){		
							j++;
							String lex =  cod.substring( i, j);			    
							Token token = new Token( lex, Token.PALABRARESEVADACICLO, j );
							return token;
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
     * Intenta extraer una palabra reservada para una desicion a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer una palabra reservada para una desicion   - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer una palabra reservada para una desicion  - 0<=i<codigo.length()
     * @return el token una palabra reservada para una desicion  o NULL, si el token en la posición dada no es una palabra reservada para una desicion . El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerPalabraReservadaDesicion( String cod, int i )
	{
		if( cod.charAt(i) =='S'){
			int j=i+1;
			if((j<cod.length() && cod.charAt(j) =='i')){	
				j++;		
				if(j<cod.length() && cod.charAt(j) =='N'){
					j++;
					if( j<cod.length() && cod.charAt(j) =='o'){		
						j++;
						String lex =  cod.substring( i, j);			    
						Token token = new Token( lex, Token.PALABRARESEVADADESICION, j );
						return token;
					}
				}else {
					String lex =  cod.substring( i, j);			    
					Token token = new Token( lex, Token.PALABRARESEVADADESICION, j );				
					return token;
				}
			}
		}
		return null;
	}
	
	/**
     * Intenta extraer una palabra reservada para una clase a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer una palabra reservada para un  clase - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer una palabra reservada para un clase - 0<=i<codigo.length()
     * @return el token una palabra reservada para un clase o NULL, si el token en la posición dada no es una palabra reservada para una clase. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerPalabraReservadaClase( String cod, int i )
	{
		if( cod.charAt(i) =='s'){
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='t'){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='r'){		
					j++;
					if( j<cod.length() && cod.charAt(j) =='u'){		
						j++;
						if( j<cod.length() && cod.charAt(j) =='c'){		
							j++;
							if( j<cod.length() && cod.charAt(j) =='t'){		
								j++;
								String lex =  cod.substring( i, j);			    
								Token token = new Token( lex, Token.PALABRARESEVADACLASE, j );
								return token;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
     * Intenta extraer una palabra reservada para una clase a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer una palabra reservada para un  clase - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer una palabra reservada para un clase - 0<=i<codigo.length()
     * @return el token una palabra reservada para un clase o NULL, si el token en la posición dada no es una palabra reservada para una clase. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerPalabraReservadaTipoDatos( String cod, int i )
	{
		if( cod.charAt(i) =='i' ){
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='s'){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='i'){		
					j++;
					if( j<cod.length() && cod.charAt(j) =='z'){		
						j++;
						if( j<cod.length() && cod.charAt(j) =='e'){		
							j++;
							String lex =  cod.substring( i, j);			    
							Token token = new Token( lex, Token.PALABRARESEVADAENTEROS, j );
							return token;
						}
					}
				}
			}
		}
		if( cod.charAt(i) =='r' ){
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='s'){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='i'){		
					j++;
					if( j<cod.length() && cod.charAt(j) =='z'){		
						j++;
						if( j<cod.length() && cod.charAt(j) =='e'){		
							j++;
							String lex =  cod.substring( i, j);			    
							Token token = new Token( lex, Token.PALABRARESEVADAREALES, j );
							return token;
						}
					}
				}
			}
		}
		if(cod.charAt(i) == '&') {
			int j= i+1;
			if(j<cod.length() && cod.charAt(j) =='(') {
				j++;
				if(j<cod.length() && cod.charAt(j) =='s') {
					j++;
					if(j<cod.length() && cod.charAt(j) =='t') {
						j++;
						if(j<cod.length() && cod.charAt(j) =='r') {
							j++;
							if(j<cod.length() && cod.charAt(j) ==')') {
								j++;
								String lex =  cod.substring( i, j);			    
								Token token = new Token( lex, Token.PALABRARESEVADACADENAS, j );
								return token;
							}
						}
					}
				}
				if(j<cod.length() && cod.charAt(j) =='c') {
					j++;
					if(j<cod.length() && cod.charAt(j) =='a') {
						j++;
						if(j<cod.length() && cod.charAt(j) =='r') {
							j++;
							if(j<cod.length() && cod.charAt(j) ==')') {
								j++;
								String lex =  cod.substring( i, j);			    
								Token token = new Token( lex, Token.PALABRARESEVADACARACTERES, j );
								return token;
							}
						}	
					}
				}
			}
		}
		return null;
	}
	
	/**
     * Intenta extraer una palabra reservada del nombre de una variable para una clase a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer una palabra reservada para un  clase - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer una palabra reservada para un clase - 0<=i<codigo.length()
     * @return el token una palabra reservada para un clase o NULL, si el token en la posición dada no es una palabra reservada para una clase. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerPalabraReservadaNombreVariable(String cod, int i) {
		if(esLetraMinuscula(cod.charAt(i)) || cod.charAt(i)=='&' || cod.charAt(i)=='#'){
			int j=i+1;
			while( j<cod.length() && (esLetraMinuscula(cod.charAt(j)) || esDigito(cod.charAt(j))
					|| cod.charAt(j)=='&' || cod.charAt(j)=='#'))		
			    	j++;
		    String lex =  cod.substring( i, j);			    
		    Token token = new Token( lex, Token.PALABRARESEVADANOMBREVARIABLES, j );
			return token;			
		}

		return null;
	}	
	/**
     * Intenta extraer una palabra reservada del nombre de un metodo para una clase a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer una palabra reservada para un  clase - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer una palabra reservada para un clase - 0<=i<codigo.length()
     * @return el token una palabra reservada para un clase o NULL, si el token en la posición dada no es una palabra reservada para una clase. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerPalabraReservadaNombreMetodo(String cod, int i) {
		if( esLetraMayuscula(cod.charAt(i)) || cod.charAt(i)=='%' || cod.charAt(i)=='!'){
			int j=i+1;
			while( j<cod.length() && (esLetraMayuscula(cod.charAt(j)) || esDigito(cod.charAt(j))
					|| cod.charAt(j)=='%' || cod.charAt(j)=='!'))		
			    	j++;
		    String lex =  cod.substring( i, j);			    
		    Token token = new Token( lex, Token.PALABRARESEVADANOMBREMETODO, j );
			return token;			
		}
		return null;
	}
	/**
     * Intenta extraer una palabra reservada del nombre de la clase a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer una palabra reservada para un  clase - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer una palabra reservada para un clase - 0<=i<codigo.length()
     * @return el token una palabra reservada para un clase o NULL, si el token en la posición dada no es una palabra reservada para una clase. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	
	public Token extraerPalabraReservadaNombreClase(String cod, int i) {
		if( cod.charAt(i)=='/' || cod.charAt(i)=='\\' ){
			int j=i+1;
			while( j<cod.length() && (esLetra(cod.charAt(j)) || esDigito(cod.charAt(j))
					|| cod.charAt(i)=='/' || cod.charAt(i)=='\\')) 		
			    	j++;
		    String lex =  cod.substring( i, j);			    
		    Token token = new Token( lex, Token.PALABRARESEVADANOMBRECLASE, j );
			return token;			
		}
		return null;
	}
	
	/**
     * Intenta extraer una palabra reservada sin categoria a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer una palabra reservada para un  clase - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer una palabra reservada para un clase - 0<=i<codigo.length()
     * @return el token una palabra reservada para un clase o NULL, si el token en la posición dada no es una palabra reservada para una clase. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
	public Token extraerPalabraReservadaSinCategoria(String cod, int i) {
		if( cod.charAt(i) =='p' ){
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='r'){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='o'){		
					j++;
					if( j<cod.length() && cod.charAt(j) =='c'){		
						j++;
						if( j<cod.length() && cod.charAt(j) =='e'){		
							j++;
							if( j<cod.length() && cod.charAt(j) =='d'){		
								j++;
								if( j<cod.length() && cod.charAt(j) =='u'){		
									j++;
									if( j<cod.length() && cod.charAt(j) =='r'){		
										j++;
										if( j<cod.length() && cod.charAt(j) =='e'){		
											j++;
											String lex =  cod.substring( i, j);			    
											Token token = new Token( lex, Token.PALABRARESEVADAPROCEDIMIENTO, j );
											return token;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if( cod.charAt(i) =='m' ){
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='e'){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='t'){		
					j++;
					if( j<cod.length() && cod.charAt(j) =='h'){		
						j++;
						if( j<cod.length() && cod.charAt(j) =='o'){		
							j++;
							if( j<cod.length() && cod.charAt(j) =='d'){		
								j++;
								String lex =  cod.substring( i, j);			    
								Token token = new Token( lex, Token.PALABRARESEVADAMETODO, j );
								return token;
							}
						}
					}
				}
			}
		}
		if( cod.charAt(i) =='c' ){
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='v'){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='a'){		
					j++;
					if( j<cod.length() && cod.charAt(j) =='r'){		
						j++;
						String lex =  cod.substring( i, j);			    
						Token token = new Token( lex, Token.PALABRARESEVADACONSTANTES, j );
						return token;
					}
				}
			}
		}
		if( cod.charAt(i) =='v' ){
			int j=i+1;
			if( j<cod.length() && cod.charAt(j) =='a'){		
				j++;
				if( j<cod.length() && cod.charAt(j) =='r'){		
					j++;
					String lex =  cod.substring( i, j);			    
					Token token = new Token( lex, Token.PALABRARESEVADAVARIABLES, j );
					return token;
				}
			}
		}
		return null;
	}
	
    /**
     * Intenta extraer un identificador de la cadena cod a partir de la posición i,
     * basándose en el Autómata
     * @param cod - código al cual se le va a intentar extraer un identficador - codigo!=null
     * @param i - posición a partir de la cual se va a intentar extraer un identificador  - 0<=indice<codigo.length()
     * @return el token identificaror o NULL, si el token en la posición dada no es un identificador. El Token se compone de 
     * el lexema, el tipo y la posición del siguiente lexema.
     */
		
	public Token extraerIdentificador ( String cod, int i)
	{
		if( cod.charAt(i)=='_' ){
			int j=i+1;
			while( j<cod.length() && esLetra(cod.charAt(j)) )		
			    	j++;
		    String lex =  cod.substring( i, j);			    
		    Token token = new Token( lex, Token.IDENTIFICADOR, j );
			return token;			
		}	
		return null;
	}

    /**
     * Extraer un lexema no reconocido de la cadena cod a partir de la posición i.
     * Antes de utilizar este método, debe haberse intentado todos los otros métodos para los otros tipos de token
     * @param cod - código al cual se le va a extraer el token no reconocido - codigo!=null
     * @param i - posición a partir de la cual se va a extraer el token no reconocido  - 0<=indice<codigo.length()
     * @return el token no reconocido. El Token se compone de lexema, el tipo y la posición del siguiente lexema.

     */
	public Token extraerNoReconocido ( String cod, int i)
	{
		String lexema =  cod.substring( i, i + 1);
		int j=i+1;
		Token token = new Token( lexema, Token.NORECONOCIDO, j );
		return token;
	}
	
	/**
     * Determina si un carácter es un dígito
     * @param caracter - Carácter que se va a analizar - caracter!=null,
     * @return true o false según el carácter sea un dígito o no
     */
	public boolean esDigito (char caracter )
	{
		return  caracter >= '0' && caracter <= '9';
	}

	/**
     * Determina si un carácter es una letra
     * @param caracter - Carácter que se va a analizar - caracter!=null,
     * @return true o false según el carácter sea una letra o no
     */
	public boolean esLetra (char caracter )
	{
		return  (caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z');
	}
	/**
     * Determina si un carácter es una letra minuscula
     * @param caracter - Carácter que se va a analizar - caracter!=null,
     * @return true o false según el carácter sea una letra o no
     */
	public boolean esLetraMinuscula (char caracter )
	{
		return   (caracter >= 'a' && caracter <= 'z');
	}
	/**
     * Determina si un carácter es una letra mayuscula
     * @param caracter - Carácter que se va a analizar - caracter!=null,
     * @return true o false según el carácter sea una letra o no
     */
	public boolean esLetraMayuscula (char caracter )
	{
		return  (caracter >= 'A' && caracter <= 'Z');
	}

}
