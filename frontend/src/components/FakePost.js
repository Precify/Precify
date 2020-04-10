import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Redirect, Switch, Link, withRouter} from "react-router-dom";
import Message from '../elements/Message';
import Error from '../elements/Error';
import { COMMON_FIELDS, REGISTRATION_FIELDS, LOGIN_FIELDS, LOGIN_MESSAGE, ERROR_IN_LOGIN } from '../MessageBundle';
import axios from 'axios';
import Button from '@material-ui/core/Button'
 
export default class FakePost extends Component {
	constructor(props) {
		super(props);
		this.state = {
			clicked : false
		}
	}

	onSubmit = async e => {		
		e.preventDefault();
		const data = {
			url : this.props.url
		};
		await axios.post('http://localhost:5000/fakePost', data)
		.then(response => {
			console.log(response);
		})
		.catch(error => {
			console.log(error.response);
		});
	}

	render(){
	        return(
	        	   <button onClick={this.onSubmit}>Fake</button>
	        );
	}
}

			