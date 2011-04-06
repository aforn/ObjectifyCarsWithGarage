package com.objectifycars;
//http://teronurminen.blogspot.com/2010/03/gaej-data-storage-persistence-with.html
import java.io.IOException;
import javax.servlet.http.*;
import com.googlecode.objectify.*;

@SuppressWarnings("serial")
public class ObjectifyCarsServlet extends HttpServlet {
	static {
		ObjectifyService.register(Person.class);
		ObjectifyService.register(Car.class);
		ObjectifyService.register(Garage.class);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		resp.setContentType("text/html");
		String html = "<form>Owner id (blank for new)<input name=\"id\" type=\"text\"> " +
		"Car license <input name=\"license\" type=\"text\"> Garage name <input name=\"name\" type=\"text\"> "+
		"City of Garage<input name=\"cityOfGarage\" type=\text\"> <input type=\"submit\"></form>";
		resp.getWriter().println(html);

		if( req.getParameter("license") == null ) {
			return;
		}
		if( req.getParameter("license").length() == 0 ) {
			resp.getWriter().println("License not set");
			return;
		}

		if( req.getParameter("name") == null ) {
			return;
		}
		if( req.getParameter("name").length() == 0 ) {
			resp.getWriter().println("Garage name not set");
			return;
		}

		if( req.getParameter("cityOfGarage") == null ) {
			return;
		}
		if( req.getParameter("cityOfGarage").length() == 0 ) {
			resp.getWriter().println("City of garage not set");
			return;
		}

		Objectify ofy = ObjectifyService.begin();


		// If an id was provided, look up that Person; otherwise, create a new Person.
		Person owner;
		if( req.getParameter("id") != null && req.getParameter("id").length() > 0 ) {
			owner = ofy.find(Person.class, Long.parseLong(req.getParameter("id")) );
		}else {
			owner = new Person();
			ofy.put(owner);
		}

		//Construct a new Car with the specified owner and the license specified on the form
		Key<Person> ownerKey = new Key<Person>(Person.class, owner.getId());
		Car car = new Car(ownerKey);
		car.setLicense(req.getParameter("license"));
		//store the new car
		ofy.put(car);

		//Construct a new garage with the specified owner and the license specified on the form
		Garage garage = new Garage(ownerKey);
		garage.setName(req.getParameter("name"));
		garage.setCityOfGarage(req.getParameter("cityOfGarage"));
		//store the new car
		ofy.put(garage);


		Query<Car> q = ofy.query(Car.class).ancestor(ownerKey);
		resp.getWriter().println("<pre>Cars and garage name and city for owner id "+owner.getId()+":");
		for( Car c : q )
			resp.getWriter().println(c.getLicense());
		Query<Garage> s = ofy.query(Garage.class).ancestor(ownerKey);
		
		for( Garage g : s )
			resp.getWriter().println(g.getName());
		
		for( Garage g : s )
			resp.getWriter().println(g.getCityOfGarage());	
	}
}



