import React from "react";
import CreditService from "../../../api/Credit/CreditService";

class CreditQueryPage extends React.Component{

    constructor(props){
        super(props)

        this.state = {

            userId:"",
            userDateOfBirth: "",
            userCredit: []
        }
        this.handlerChange = this.handlerChange.bind(this);
        this.handleResponse = this.handleResponse.bind(this);
    }

    handlerChange(event) {
        
        console.log("handleChange called.");
        this.setState({ [event.target.name]: event.target.value })
    }

    handleFormSubmit = (e) => {

        console.log("user id: " + this.state.userId);
        console.log("user birth date: " + this.state.userDateOfBirth);

        e.preventDefault();
        CreditService.CreditQueryResult(this.state.userId, this.state.userDateOfBirth)
        .then(response => this.handleResponse(response))
        .catch(error => this.handleError(error));  
    }

    handleResponse(response) {
        this.setState({userCredit : response.data})
        document.getElementById("alertDiv").style.visibility="hidden";
        document.getElementById("tableCreditResult").style.visibility="visible";

        console.log(this.state.userCredit);   
    }


    handleError(error) {

        let errorText = document.createTextNode(error.response.data.message);
        document.getElementById("alertDiv").style.visibility="visible";
        document.getElementById("tableCreditResult").style.visibility="hidden";
        document.getElementById("alertText").appendChild(errorText);

        console.log(error.response)
    }

    render(){
        const { userCredit } = this.state
        return(
            <>
            <h1>Credit Query Page</h1>
            <div className="container">
                <div className="text-center mt-5">
                    <div className="row col-md-4 offset-md-4">
                        <form className="form-signin" onSubmit={this.handleFormSubmit}>
                            <h2 className="h3 mb-3 font-weight-normal">User Information</h2>
                            <label htmlFor="inputUserId" className="sr-only">User Identity Number</label>
                            <input
                                type="text"
                                id="inputUserId"
                                className="form-control"
                                placeholder="user identity number"
                                required={true}
                                name="userId"
                                onChange={this.handlerChange}
                            />
                            <label htmlFor="inputUserDateOfBirth" className="sr-only">Date of Birth</label>
                            <input
                                type="Date"
                                id="inputUserDateOfBirth"
                                className="form-control"
                                required={true}
                                name="userDateOfBirth"
                                onChange={this.handlerChange}
                            />
                            <input type="submit" className="btn btn-primary btn-block" value="Query" style={{color: "white"}}/>
                        </form>
                    </div>
                </div>
                <div>
                    <table id="tableCreditResult" className="table table-dark" style={{visibility:"hidden"}}>
                        <thead>
                            <tr>
                                <th>Credit Result</th>
                                <th>Credit Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr key={userCredit.id}>
                                <td>{userCredit.creditResult}</td>
                                <td>{userCredit.creditAmount}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="alertDiv" style={{visibility:"hidden"}}>
                    <p id="alertText" className="alert alert-warning" role="alert"></p>
                </div>
            </div>
            </>
        )
    }
}

export default CreditQueryPage;