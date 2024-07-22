'use strict';

class Create extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            city: '',
            date: '',
            maxPrice: '',  // Updated to match the form input field name
            searchResults: ''  // Optional, remove if not used
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleAlert = () => {
        alert("Succesfully created :)");
    };

    handleChange(event) {
        const { name, value } = event.target;
        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        event.preventDefault();
        // AJAX
        this.llamadaAjax(this.state.name, this.state.city, this.state.date, this.state.maxPrice);

        console.log('Search criteria:', this.state);
    }

    llamadaAjax(name, city, date, maxPrice) {
        const requestData = {
            name: name,
            city: city,
            date: date,
            maxPrice: maxPrice
        };

        fetch('http://localhost:8080/EventEase//CreateEvent', {  // Update URL to the correct endpoint
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        })
            .then(data => {
                console.log('Search results:', data);
                this.handleAlert();
            })
            .catch(error => {
                console.error('There was an error making the request:', error);
            });
    }

    render() {
        return (
            <div>
                <button name="view"
                    style={{ backgroundColor: "red"}}
                    onClick={(e) => this.props.onNavigate(e)}>Back</button><br></br>
                <form onSubmit={this.handleSubmit}>
                    <p style={{ color: "white", border: "solid black", backgroundColor: "#085394", fontSize: "30px" }}>Create the best events!</p>
                    <div>
                        <input
                            type="text"
                            name="name"
                            placeholder="Name of Event"
                            style={{ width: '100%' }}
                            value={this.state.name}
                            onChange={this.handleChange}
                        />
                    </div>
                    <div>
                        <label>
                            City <br />
                            <select
                                name="city"
                                value={this.state.city}
                                onChange={this.handleChange}
                            >
                                <option value="">Select a city</option>
                                <option value="Madrid">Madrid</option>
                                <option value="Barcelona">Barcelona</option>
                                <option value="Valencia">Valencia</option>
                                {/* Add more cities as needed */}
                            </select>
                        </label>
                    </div>
                    <div>
                        <label>
                            Date <br />
                            <input
                                type="date"
                                name="date"
                                value={this.state.date}
                                onChange={this.handleChange}
                            />
                        </label>
                    </div>
                    <div>
                        <label>
                            Price <br />
                            <input
                                type="number"
                                name="maxPrice"
                                value={this.state.maxPrice}
                                onChange={this.handleChange}
                            /> €
                        </label>
                    </div>
                    <div>
                        <label>
                            Number of Tickets <br />
                            <input
                                type="number"
                                name="numberOfTickets"  // Ensure this is handled if part of state
                                value={this.state.numberOfTickets}
                                onChange={this.handleChange}
                            />
                        </label>
                    </div>
                    <div>
                        <button type="submit">Create</button>
                    </div>
                </form>
            </div>
        );
    }
}
