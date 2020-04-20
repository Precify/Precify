import React from "react";
import { Alert } from "reactstrap";

const Output = ({ val }) => {
  let Result;
  if (val === -1) {
    Result = <Alert color="secondary">Cannot classify!</Alert>;
  } else if (val < 0.5) {
    Result = <Alert color="primary">You can trust on the news!</Alert>;
  } else {
    Result = <Alert color="danger">The news is most likely to be fake!</Alert>;
  }
  return (
    <div className="container mt-5">
      <div className="d-flex mt-3">{Result}</div>
    </div>
  );
};
export default Output;
