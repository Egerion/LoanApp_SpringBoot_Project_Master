import React, { Component } from 'react'
import axios from 'axios'

class UserList extends Component {

	constructor(props) {
		super(props)

		this.state = { posts: [], errorMsg: ''}
	}

	componentDidMount() {
		axios
			.get('http://localhost:8081/user/list')
			.then(response => {
				console.log(response)
				this.setState({ posts: response.data })
			})
			.catch(error => {
				console.log(error)
			this.setState({errorMsg: 'Error retrieving data'})
		})
	}

	render() {
		const { posts, errorMsg } = this.state
		console.log(posts);
		return (
			<div>
				<table className="table table-dark">
					<thead>
						<tr>
							<th>Name</th>
							<th>Surname</th>
							<th>Age</th>
							<th>Salary</th>
							<th>Guarantee Type</th>
						</tr>
					</thead>
					<tbody>
						{
							posts.map((person) => (
								<tr key={person.id}>
									<td>{person.name}</td>
									<td>{person.surname}</td>
									<td>{person.age}</td>
									<td>{person.salary}</td>
									<td>{person.enumGuaranteeType}</td>
									<td/>
								</tr>
							))
						}
					</tbody>
            	</table>
			</div>
		)
	}
}

export default UserList