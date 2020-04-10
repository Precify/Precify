import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Redirect, Switch, Link, withRouter} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.css';
import Message from '../elements/Message';
import Error from '../elements/Error';
import { COMMON_FIELDS, REGISTRATION_FIELDS, LOGIN_FIELDS, LOGIN_MESSAGE, ERROR_IN_LOGIN } from '../MessageBundle';
import axios from 'axios';
import Button from '@material-ui/core/Button'
 
export default class Logout extends Component {
	constructor(props) {
		super(props);
		this.onSubmit = this.onSubmit.bind(this);
	}
	async onSubmit(e) {
		await localStorage.setItem('user_name', "");
		await localStorage.setItem('password', "");
		await localStorage.setItem('session', "end");
		await localStorage.clear();
	}
	render() {	
		return (
			<div>
			<Link to="/UserSignIn" className='nav-item nav-link' onClick={this.onSubmit} >LOGOUT</Link>
			</div>
			);
		}
}
			