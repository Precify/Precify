import React from "react";
import { MDBCol, MDBContainer, MDBRow, MDBFooter } from "mdbreact";
import "bootstrap/dist/css/bootstrap.min.css";

const Footer = () => {
  return (
    <div className = 'container-fluid height-full bg-secondary'>
    <h4>
    <MDBFooter color="blue" className="font-small pt-4 mt-4">
      <MDBContainer fluid className="text-center text-md-left">
        <MDBRow>
          <MDBCol md="5">
            <h2 className="text-light">PRECIFY</h2>
            <p className="text-light"><b>
              PRECISE COVID NEWS
            </b></p>
          </MDBCol>
          <MDBCol md="2">

            <ul>
              <li className="list-unstyled">
                <a href="#!" className="text-light">BLOG</a>
              </li>
              <li className="list-unstyled">
                <a href="#!" className="text-light">ABOUT</a>
              </li>
              <li className="list-unstyled">
                <a href="#!" className="text-light">API</a>
              </li>
              <li className="list-unstyled">
                <a href="#!" className="text-light">HELP</a>
              </li>
            </ul>
          </MDBCol>
           <MDBCol md="2">

            <ul>
              <li className="list-unstyled">
                <a href="#!" className="text-light">TEAMS</a>
              </li>
              <li className="list-unstyled">
                <a href="#!" className="text-light">TALENT</a>
              </li>
              <li className="list-unstyled">
                <a href="#!" className="text-light">ADVERTISING</a>
              </li>
              <li className="list-unstyled">
                <a href="#!" className="text-light">ENTERPRISE</a>
              </li>
            </ul>
          </MDBCol>
          <MDBCol md="3">
            <a className="navbar-brand" href="#">
            
            </a>
             <MDBContainer fluid className="text-light">
          &copy; {new Date().getFullYear()} COPYRIGHT<a href="https://www.mdbootstrap.com" className="text-light"> PRECIFY </a>
        </MDBContainer>
          </MDBCol>
          
        </MDBRow>
      </MDBContainer>
     
    </MDBFooter>
    </h4>
    </div>
  );
}

export default Footer;