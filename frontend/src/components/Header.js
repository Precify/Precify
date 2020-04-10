import React, { Component } from 'react';
import { Link } from "react-router-dom";
import classNames from 'classnames';
import Error from '../elements/Error';
import { REGISTRATION_FIELDS, REGISTRATION_MESSAGE, COMMON_FIELDS, ERROR_IN_REGISTRATION } from '../MessageBundle';
import axios from 'axios';

export default class Header extends Component {
	constructor(props) {
		super(props);
		this.state = {
		};
	}

	render() {
		return (
			<div>
			<nav className='navbar navbar-expand-lg navbar-dark bg-dark'>
			<a className="navbar-brand" href="#">
            
          	</a>
          	<h1 className="navbar-text"><b>PRECIFY</b></h1>      
          	<h4 className="navbar-text"><b>
			<a href = {"https://github.com/"}>Download Precify App</a> </b></h4>               
			</nav>

			
			<nav className='navbar navbar-expand-lg navbar-light header'>
			<a className="navbar-brand" href="#">
            <h1><b></b></h1>
          	</a>

			<div className="nav navbar-nav ml-auto">

          		<Link to="/Headlines" className='nav-item nav-link'>HEADLINES</Link>
           		<Link to="/AdminSignIn" className='nav-item nav-link'>ADMIN LOGIN</Link>
           		<Link to="/Statistics" className='nav-item nav-link'>STATISTICS</Link>
           	
            </div>
			</nav>			
			</div>
		)
	}
}
// <img className="logo" src = {require('./Logo.png')} />
            
/*
 <Link to="/DROSignIn" className='nav-item nav-link'>DRO LOGIN</Link>
          	<Link to="/DoctorSignIn" className='nav-item nav-link'>DOCTOR LOGIN</Link>
            <Link to="/DoctorSignUp" className='nav-item nav-link'> DOCTOR SIGNUP</Link>
			<Link to="/DoctorSignInAdmin" className='nav-item nav-link'> ADMIN DOCTOR</Link>
 			 	
 			 */