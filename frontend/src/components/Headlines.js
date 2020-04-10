import React, {Component} from 'react';
import { BrowserRouter as Router, Route, Redirect, Switch, Link} from "react-router-dom";
import axios from 'axios';
import "bootstrap/dist/css/bootstrap.min.css";
 
import Logout from './Logout';
import HeadlinesList from './HeadlinesList';
import Header from './Header';
import Footer from './Footer';
import Logo from './Logo.png';

export default class Headlines extends Component {
    constructor(props) {
        super(props);
        this.state = {
            applications: []
        };
    }
    componentDidMount() {
        axios.get('http://localhost:5000/getAllTruePosts')
            .then(response => { 
                this.setState({
                    applications: response.data
                });
            })
            .catch(function(error) {
                console.log(error);
            });
    }
    applicationList() { 
        return this.state.applications.map(function(currentApplication, i) {
            return <HeadlinesList application={currentApplication} key={i} />;
        })
    }
    render() {
        return (
                <div>
                    <Header/>
                    <center>
                    <div>
                        <h3>Headlines</h3>
                        <table className = 'table table-striped' style={{marginTop: 20}}>
                            <thead>
                                <tr>
                                    <th><h2> Source</h2> </th>
                                    <th><h2> SUMMARY </h2></th>
                                    <th><h2> VIEW FULL POST </h2></th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.applicationList()}
                            </tbody>
                        </table>
                    </div>
                    </center>
                    <Footer />
                </div>
        )
    }
}
