import React, { Component } from "react";
import { BrowserRouter as Router, Route, Redirect, Switch } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.css';
import axios from 'axios';
import { Link } from "react-router-dom";
import classNames from 'classnames';
import Error from '../elements/Error';
import { REGISTRATION_FIELDS, REGISTRATION_MESSAGE, COMMON_FIELDS, ERROR_IN_REGISTRATION } from '../MessageBundle';
import Header from './Header';
import Footer from './Footer';

export default class Statistics extends React.Component {
	constructor(props) {
		super(props)
		this.state = {
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
		
	}

	componentWillMount() {
		localStorage.setItem('session_start', null);	
	}

	render() {
		return (
			<div>
			<Header />
 			<center>
				<div className="jumbotron">
					<h2>STAT<span className="change-color">ISTICS</span> </h2>
					<hr />
				</div>
				</center>
				<Footer/>
			</div>
		)

	}
}
