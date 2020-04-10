import React, { Component } from "react";
import { BrowserRouter as Router, Route, Redirect, Switch } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.css';
import axios from 'axios';
import { Link } from "react-router-dom";
import classNames from 'classnames';
import Error from '../elements/Error';
import { REGISTRATION_FIELDS, REGISTRATION_MESSAGE, COMMON_FIELDS, ERROR_IN_REGISTRATION } from '../MessageBundle';
import LoggedIn from './LoggedIn';
import Header from './Header';
import Footer from './Footer';
import Logo from './Logo.png';

export default class AdminSignIn extends React.Component {
	constructor(props) {
		super(props)
		this.state = {
			user_name : "",
			password : "",
			error : false,
			loginSuccess : false,
			errorMessage : ""
		}
		this.handleChange = this.handleChange.bind(this)
	}

	handleChange(event) {
		const {name, value} = event.target
		this.setState({
			[name]: value
		})
	}

	onSubmit = async e => {
		// const data = {
		// 	user_name : this.state.user_name,
		// 	password : this.state.password
		// };
		// var res;
		if(this.state.user_name == 'admin' && this.state.password == 'admin'){
			this.setState({
				loginSuccess : true
			});
		}
		// await axios.post('http://localhost:4001/LeoHelp/loginIn', data)
		// .then(response => {
		// 	console.log(response);
		// 	res = response.status;
		// })
		// .catch(error => {
		// 	console.log(error.response);
		// 	this.setState({
		// 		password: ""
		// 	});
		// });
		
		// if(res === 200) {
		// 	console.log("IN");
		// 		this.setState({
		// 		loginSuccess : true
		// 		});
		// } else {
		// 	console.log("1234")
		// 	this.setState({
		// 		errorMessage: "Username or password is incorrect",
		// 		password: ""
		// 	});
		// }
	
	}

	componentWillMount() {
		localStorage.setItem('session_start', null);	
	}

	render() {

		const { loginSuccess, error } = this.state;
		if (this.state.loginSuccess == true) {
			localStorage.setItem('session', "start");
			localStorage.setItem('user_name', this.state.user_name);
			console.log("%%%%%%");
			console.log(this.state.user_name);
			console.log("%%%%%%");
			
			return <Redirect push to  = {{ 
					pathname : '/LoggedIn',
            		state : { user_name : this.state.user_name}
            }}
			/>;
		}

		return (
		<div  className='bg-dark'>
			<div >
			<Header />
 			<center>
				<div className="jumbotron">
					<h2>ADMIN <span className="change-color">LOGIN</span> </h2>
					<hr />
					<form onSubmit = {this.handleSubmit}>
					<div className="form-group">
						<div className="row">
							<div className="col-md-3">
								<label htmlFor="user_name"><b>ADMIN<span className="change-color"> NAME</span> </b>  </label>
							</div>
							<div className="col-md-9">
								<input type="text" className="form-control form-control-lg" value={this.state.user_name} name="user_name" placeholder="username" id="username" onChange={this.handleChange}/>
							</div>
						</div>
					</div>

					<div className="form-group">
						<div className="row">
							<div className="col-md-3">
								<label htmlFor="password"><b>PASS<span className="change-color"> WORD</span>  </b></label>
							</div>
							<div className="col-md-9">
								<input type="password" className="form-control form-control-lg" name="password" id="password" value={this.state.password} placeholder="Password" onChange={this.handleChange} />
							</div>
						</div>
					</div>

					<h4><span className="errorMessage">{this.state.errorMessage}</span></h4>

						<br/><br/>
						<center> <button type="button" onClick={this.onSubmit} className="btn btn-primary"><h4>LOGIN</h4></button><br /><br />
						</center>
								
					</form>
				</div>
				</center>
			</div>
			<br/><br/><br/><br/>
			<br/><br/><br/><br/>
			<br/><br/>
			<Footer />
			</div>
		)

	}
}
