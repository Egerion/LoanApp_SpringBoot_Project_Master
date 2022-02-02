import React from "react";
import UserList from "./UserList";

class UserListPage extends React.Component{

    constructor(props){
        super(props)
    }

    render(){
        return(
            <div className="container">
                <div className="text-center mt-5">
                    <h2>Registered User List</h2>
                    <UserList></UserList>
                </div>
            </div>
        )
    }
}

export default UserListPage;