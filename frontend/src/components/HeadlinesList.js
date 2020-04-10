import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Redirect, Switch, Link, withRouter} from "react-router-dom";
import Message from '../elements/Message';
import Error from '../elements/Error';
import { COMMON_FIELDS, REGISTRATION_FIELDS, LOGIN_FIELDS, LOGIN_MESSAGE, ERROR_IN_LOGIN } from '../MessageBundle';
import axios from 'axios';
import Button from '@material-ui/core/Button'
	
export default class HeadlinesList extends Component {
	constructor(props) {
		super(props);
	} 
	render(){
	        return( 
	            <tr>
	                <td><h4>{this.props.application.source}</h4></td>
	                <td><h4>{this.props.application.summary}</h4></td>
	                <td><a href = {this.props.application.url}><h4>{this.props.application.url}</h4></a></td>
	            </tr>
	        );
	   
	}

}
			