import React, {Component} from 'react';
import { BrowserRouter as Router, Route, Redirect, Switch, Link} from "react-router-dom";
import axios from 'axios';
import "bootstrap/dist/css/bootstrap.min.css";
 
import ViewPostList from './ViewPostList';
import SHeader from './SHeader';
import Footer from './Footer';
import Logo from './Logo.png';

export default class AllPosts extends Component {
    constructor(props) {
        super(props);
        this.state = {
            applications: []
        };
    }
    componentDidMount() {
        axios.get('http://localhost:5000/getAllPosts')
            .then(response => { 
                console.log(response.data)
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
            return <ViewPostList application={currentApplication} key={i} />;
        })
    }
    render() {
        return (
            <div styles={{ backgroundImage:`url(${Logo})` }}>
                <div className = 'container'>
                    <SHeader/>
                    <div>
                        <table className = 'table table-striped' style={{marginTop: 20}}>
                            <thead>
                                <tr>
                                    <th><h3> URL</h3></th>
                                    <th><h3> SOURCE </h3></th>
                                    <th><h3> AUTHOR </h3></th>
                                    <th><h3> TYPE </h3></th>    
                                    <th><h3> BUTTON </h3></th>            
                                </tr>
                            </thead>
                            <tbody>
                                {this.applicationList()}
                            </tbody>
                        </table>
                    </div>
                    <Footer/>
                </div>
            </div>
        )
    }
}
