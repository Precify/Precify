import React, { Component } from "react";
import { BrowserRouter as Router, Route, Redirect, Switch } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.css';
import axios from 'axios';
import { Link } from "react-router-dom";
import className from 'classnames';
import Error from '../elements/Error';
import { REGISTRATION_FIELDS, REGISTRATION_MESSAGE, COMMON_FIELDS, ERROR_IN_REGISTRATION } from '../MessageBundle';
import 'bootstrap/dist/css/bootstrap.min.css';
import Logout from './Logout'; 
import Footer from './Footer';
import Header from './Header';

export default class LoggedIn extends Component {
	constructor(props) {
		super(props)
		this.state= {
		
		}
		// console.log(this.state.user_name)
	}



	componentDidMount(){
		
	}

	onSubmit = async e => {
		e.preventDefault();
	}
	onSubmit1 = async e => {
		e.preventDefault();
	}
	render() {
		return (
			<div className="user-panel">
			<nav className='navbar navbar-expand-lg navbar-dark bg-dark'>
			<a className="navbar-brand" href="#">
            <img className="logo" src = {require('./Logo.png')} />
            
          	</a>
          	<h1 className="navbar-text"><b>PRECIFY</b></h1>      
			</nav>

			
			<nav className='navbar navbar-expand-lg navbar-light header'>
			
			<a className="navbar-brand" href="#">
            <h1><b>PRECIFY ADMIN DASHBOARD</b></h1>
          	</a>
			
			<div className="nav navbar-nav ml-auto">

			<Logout/>
          	
            </div>
              
			</nav>

			<div className="user">
			<div className="row">
				<div className="col-md-10">

				<br/>
				<center>
				<div className="container p-3 my-3 bg-dark text-white">
				<h2>PINNED <span className="change-color">POSTS</span><span className="change-color"></span></h2>
				<Link to={{
					  pathname: '/AllPosts',
					}} className="btn btn-primary"><h4><b>POSTS</b></h4></Link>
				</div>
				<br/>

				</center>


				<center>
				<div className="container p-3 my-3 bg-dark text-white">
				<h2>RUN <span className="change-color">FAKE</span> NEWS<span className="change-color"> DETECTION</span> TEST</h2>
				<Link to={{
					  pathname: '/RunFakeDetectionTest',
					}} className="btn btn-primary"><h4><b>RUN</b></h4></Link>
				</div>
				<br/>
				</center>

				<center>
				<div className="container p-3 my-3 bg-dark text-white">
				<h2>RECENTLY <span className="change-color">SCARPED</span> DATA<span className="change-color"></span></h2>
				<Link to={{
					  pathname: '/RunFakeDetectionTest',
					}} className="btn btn-primary"><h4><b>DATA</b></h4></Link>
				</div>
				<br/>
				</center>
					
				<br/><br/>						<br/><br/>
		
				<div className = 'container  p-3 my-3 bg-dark text-white'>

					<center>
				
				</center>

				</div>

				</div>
				
			</div>
			</div>
			<Footer />
			</div>
		)
	}
}