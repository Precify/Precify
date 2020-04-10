import React, { Component } from 'react';
import { Link } from "react-router-dom";
import classNames from 'classnames';
import Error from '../elements/Error';
import { REGISTRATION_FIELDS, REGISTRATION_MESSAGE, COMMON_FIELDS, ERROR_IN_REGISTRATION } from '../MessageBundle';
import axios from 'axios';
import Logout from './Logout';
export default class SHeader extends Component {
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
            <img className="logo" src = {require('./Logo.png')} />
            
          	</a>
          	<h1 className="navbar-text"><b>PRECIFY PLATFORM</b></h1>      
          		<Logout />
          	</nav>	
			</div>
		)
	}
}