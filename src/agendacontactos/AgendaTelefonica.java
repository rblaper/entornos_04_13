package agendacontactos;
import java.util.ArrayList;


public class AgendaTelefonica {
	
	//propiedad lista con los contactos que se guardan en la agenda
	private ArrayList<Contacto> agenda;
	
	//constructor �nico de la clase
	public AgendaTelefonica (){
		this.agenda = new ArrayList<Contacto>();
	}
	

	
	/**
	 * M�todo para agregar un nuevo contacto al final de la lista -agenda
	 * Si ya existe otro contacto con el mismo nombre y apellidos, no lo agrega
	 * @param c: objeto de la clase Contacto con el contacto a agregar
	 * @return: true si se agerega correctamente, false en otro caso
	 */
	public boolean agregarContacto (Contacto c){
		try {
			//comprobamos si existe
			if (buscarContacto( c.getApellidos(), c.getNombre()) > -1){
				// Si el contacto existe, lanzamos una excepcion
				throw new IllegalArgumentException ("Esta persona ya existe en la agenda");
			}
			
			//en caso contrario, lo agregamos
			agenda.add(c);
			return true;
			
		} catch (IllegalArgumentException e) {
			return false;
		}
		
	}
	
	/**
	 * M�todo para agregar un nuevo contacto en una determinada posici�n de la agenda
	 * Si ya existe otro contacto con el mismo nombre y apellidos, no lo agrega
	 * @param pos: posici�n en la que insertar en contacto 
	 * @param c: objeto de la clase Contacto con el contacto a agregar
	 * @return: true si se agerega correctamente, y false en alguno de estos casos:
	 *  	1- El contacto ya est� duplicado
	 *  	2- La posici�n indicada no existe en la agenda
	 */
	public boolean agregarContacto (int pos, Contacto c){
		try {
			//comprobamos si existe
			if (buscarContacto( c.getApellidos(), c.getNombre()) > -1){
				// Si el contacto existe, lanzamos una excepcion
				throw new IllegalArgumentException ("Esta persona ya existe en la agenda");
			}
			//en caso contrario, tratatamos de insertarlo en esa posici�n
			agenda.add(pos, c);
			return true;
		}
		catch ( IndexOutOfBoundsException e){
			System.out.println("La posici�n que has indicado no existe en la agenda");
			return false;
		}
		catch ( IllegalArgumentException e){
			System.out.println("Esta persona ya existe en la agenda");
			return false;
		}
			
		
	}
	
	/**
	 * M�todo para obtener un listado de los contactos de la agenda, 
	 */
	public void listarAgenda(){
		
		System.out.println("\n\t NOMBRE \t\t APELLIDOS \t\t TELEFONO");
		System.out.println("------------------------------------------------------");
		
		for (Contacto persona: this.agenda) {
		
			System.out.format("\n\t %s \t\t %s \t\t %s", persona.getNombre() ,
					persona.getApellidos(),
					persona.getTelefono());
			
		}
	}//fin del m�todo
	
	
	/**
	 * M�todo para buscar un contacto en la agenda por nombre y apellidos
	 * @param nom: CAdena con el nombre de b�squeda
	 * @param ape: CAdena con los apellidos de b�squeda
	 * @return: Posici�n de la lista (desde 0) donde se encuentra el contacto /-1 si no se encuentra
	 * La b�squeda no diferencia entre may�sculas y min�sculas
	 * El método es private para que se pueda llamar solamente desde la propia clase
	 */
	private int buscarContacto (String nom, String ape){
		boolean encontrado =false; 
		Contacto aux;
		int i=0;
		//si la agenda no está vacía 
		if (agenda.size()>0) {
			while (!encontrado && i< agenda.size()){
			
				aux = agenda.get(i);
				if ( aux.getApellidos().compareToIgnoreCase(ape)==0 &&
						aux.getNombre().compareToIgnoreCase(nom)==0)
					encontrado= true;
				else
					i++;
			} //fin del bucle while
		} //fin del if
		//devolvemos la posición donde se encontró el contacto
		return encontrado ? i : -1;
	}
	
	
	/**
	 * M�todo para buscar un contacto en la agenda por nombre y apellidos y devolver su tel�fono
	 * @param nom: CAdena con el nombre de b�squeda
	 * @param ape: CAdena con los apellidos de b�squeda
	 * @return: Cadena con el tel�fono del contacto buscado, o "No Encontrado" si no se encuentra
	 * La b�squeda no diferencia entre may�sculas y min�sculas
	 */
	public String localizarContacto (String nom, String ape){
		int indice= buscarContacto (nom, ape);
		if (indice > 0){
			return agenda.get(indice).getTelefono();
			
		} else {
			return "No Encontrado";
		}
	}
		
	/**
	 * M�todo para buscar un contacto en la agenda por n�mero de tel�fono
	 * @param tel : CAdena con el n�mero de tel�fono buscado
	 * @return: Cadena con la combinaci�n de nombres y apellidos, o "No encontrado" si no se encuentra
	
	 */
		
		public String localizarContacto (String tel){
			boolean encontrado =false; 
			Contacto aux=null;
			int i=0;
			while (!encontrado && i< agenda.size()){
			
				aux = agenda.get(i);
				if ( aux.getTelefono().compareTo(tel)==0)
					encontrado= true;
				else
					i++;
			} //fin del bucle while
			return encontrado ? aux.getNombre()+" "+aux.getApellidos()  : "No encontrado";
		
	}
		
		/**
		 * M�todo para buscar un contacto en la agenda por nombre y apellidos y eliminarlo
		 * @param nom: CAdena con el nombre de b�squeda
		 * @param ape: CAdena con los apellidos de b�squeda
		 * @return: true si encuentra el contacto y lo elimina; False en otro caso
		 * La b�squeda no diferencia entre may�sculas y min�sculas
		 */
		public boolean eliminarContacto (String nom, String ape){
			int indice= buscarContacto (nom, ape);
			if (indice >0){
				agenda.remove(indice);
				return true;
			}
			// en caso de no encontrarlo, devolvemos Falso
			return false;
			
		}
		
		
	
	

}
