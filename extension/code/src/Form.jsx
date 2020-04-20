import React from "react";
import { Spinner } from "reactstrap";
import Output from "./Output";

class Form extends React.Component {
  state = {
    flag: false,
    loading: false,
    gradient: 0.4,
    url: "",
    source: "",
    text: "",
  };
  urlHandler = (e) => {
    this.setState({
      url: e.target.value,
    });
  };
  sourceHandler = (e) => {
    this.setState({
      source: e.target.value,
    });
  };
  textHandler = (e) => {
    this.setState({
      text: e.target.value,
    });
  };
  submitHandler = (e) => {
    e.preventDefault();
    this.setState({ loading: true });
    let data = {
      url: this.state.url,
      source: this.state.source,
      text: this.state.text,
    };
    console.log(data);
    let url = "https://stark-anchorage-45962.herokuapp.com/addPost";
    fetch(url, {
      method: "POST",
      body: JSON.stringify(data),
      headers: { "Content-Type": "application/json" },
    })
      .then((raw) => raw.json())
      .then((res) => {
        console.log(typeof res);
        this.setState({
          gradient: res,
          loading: false,
          flag: true,
        });
        console.log(res);
      })
      .catch((e) => console.log(e));
  };
  render() {
    return (
      <>
        <form onSubmit={this.submitHandler} className="container">
          <h3 className="text-center text-italic">Precify</h3>
          <div className="form-group">
            <label htmlFor="formGroupExampleInput">URL</label>
            <input
              type="text"
              className="form-control"
              id="formGroupExampleInput"
              placeholder="www.example.com"
              onChange={this.urlHandler}
              value={this.state.url}
            />
          </div>
          <div className="form-group">
            <label htmlFor="formGroupExampleInput">Source Name</label>
            <input
              type="text"
              className="form-control"
              id="formGroupExampleInput"
              placeholder="example source"
              onChange={this.sourceHandler}
              value={this.state.source}
            />
          </div>
          <div className="form-group">
            <label htmlFor="formGroupExampleInput">Text Content</label>
            <textarea
              className="form-control"
              id="exampleFormControlTextarea1"
              rows="3"
              placeholder="Enter text here if you have"
              onChange={this.textHandler}
              value={this.state.text}
            ></textarea>
          </div>
          <button type="submit" className="btn btn-primary">
            Check
          </button>
        </form>
        {this.state.loading ? (
          <div className="container mt-5">
            <Spinner type="grow" color="primary" />
            <Spinner type="grow" color="secondary" />
            <Spinner type="grow" color="success" />
            <Spinner type="grow" color="danger" />
          </div>
        ) : // <Spinner color="primary" />
        this.state.flag ? (
          <Output val={this.state.gradient} />
        ) : null}
      </>
    );
  }
}

export default Form;
