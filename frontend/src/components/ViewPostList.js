import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Redirect, Switch, Link, withRouter} from "react-router-dom";
import Message from '../elements/Message';
import Error from '../elements/Error';
import { COMMON_FIELDS, REGISTRATION_FIELDS, LOGIN_FIELDS, LOGIN_MESSAGE, ERROR_IN_LOGIN } from '../MessageBundle';
import axios from 'axios';
import Button from '@material-ui/core/Button'

import FakePost from './FakePost';

export default class ViewPostList extends Component {
	constructor(props) {
		super(props);
	}
	render(){
	        return( 
	            <tr>
	            	<td><h4>{this.props.application.url}</h4></td>
	            	<td><h4>{this.props.application.source}</h4></td>
	                <td><h4>{this.props.application.author}</h4></td>
	                <td><h4>
	                {this.props.application.fake == true &&        
						 	<div>
						  		FAKE
						  	</div> 
						}
	                {this.props.application.fake == false &&        
						 	<div>
						  		REAL
						  	</div> 
						}
					</h4></td>
	                <td><h4><FakePost url = {this.props.application.url} /></h4></td>	
	            </tr>
	        );
	   
	}

}
			