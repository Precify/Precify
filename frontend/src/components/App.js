import React, { Component } from "react";
import { BrowserRouter as Router, Route, Redirect, Switch } from "react-router-dom";
import AdminSignIn from './AdminSignIn';
import LoggedIn from './LoggedIn';
import Statistics from './Statistics';
import AllPosts from './AllPosts';
import Footer from './Footer';
import Headlines from './Headlines';

class App extends Component {
	render() {
	
		return (
			<div>
				<Router>
					<div className="App">
					<Switch>
						<Route exact path="/LoggedIn" component={LoggedIn} />
						<Route exact path="/AdminSignIn" component={AdminSignIn} />
						<Route exact path="/Statistics" component={Statistics} />
						<Route exact path="/AllPosts" component={AllPosts} />
						<Route exact path="/Footer" component={Footer} />
						<Route exact path="/Headlines" component={Headlines} />
						<Route exact path="/Statistics" component={Statistics} />
						<Redirect from="/" to="/Headlines" />
					</Switch>
					</div>
				</Router>
				<link
				  rel="stylesheet"
				  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
				  crossOrigin="anonymous"
				/>
			</div>
		);
	}
}
export default App;
