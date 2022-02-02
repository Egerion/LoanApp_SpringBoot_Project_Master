import { Route, Routes } from 'react-router-dom';
import './App.css';
import React from 'react';
import Menu from './component/menu/Menu';
import HomePage from './component/home/HomePage';
import UserListPage from './component/user/list/UserListPage';
import CreditQueryPage from './component/credit/query/CreditQueryPage';
import CreditApplyPage from './component/credit/apply/CreditApplyPage';

class App extends React.Component {
  render() {

    console.log("main App called");

    return (
      <div className="App">
        <Menu></Menu>
        <Routes>
          <Route path="/" exact element={<HomePage></HomePage>}></Route>
          <Route path="/user/list" element={<UserListPage></UserListPage>}></Route>
          <Route path="/credit/register" element={<CreditApplyPage></CreditApplyPage>}></Route>
          <Route path="/credit/query" element={<CreditQueryPage></CreditQueryPage>}></Route>
        </Routes>
      </div>
    );
  }
}

export default App;
