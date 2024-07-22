'use strict';

class App extends React.Component {
    constructor() {
        super();
        this.state = {
            page: "home",
            eventList: []
        }
        this.onNavigate = this.onNavigate.bind(this);
        this.llamadaAjax = this.llamadaAjax.bind(this);
    }

    onNavigate(e) {
        this.setState({
            page: e.target.name
        });
    };

    llamadaAjax() {
        fetch('http://localhost:8080/EventEase/Admin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: ""
        })
            .then(response => response.json())
            .then(data => {
                this.setState({ eventList: data });
                console.log('Search results:', data);
            })
            .catch(error => {
                console.error('There was an error making the request:', error);
            });
    }

    componentDidMount() {
        this.llamadaAjax();
        this.intervalId = setInterval(this.llamadaAjax, 2000);
    }

    componentWillUnmount() {
        clearInterval(this.intervalId);
    }

    render() {
        let pageContent;

        switch (this.state.page) {
            case "home":
                pageContent = <HomePage color="#6aa84f" onNavigate={this.onNavigate} />;
                break;
            case "history":
                pageContent = <History eventList={this.state.eventList} onNavigate={this.onNavigate} />;
                break;
            case "view":
                pageContent = <ViewEvents eventList={this.state.eventList} onNavigate={this.onNavigate} />;
                break;
            case "create":
                pageContent = <Create onNavigate={this.onNavigate} />;
                break;
            default:
                pageContent = <HomePage color="#6aa84f" onNavigate={this.onNavigate} />;
        }

        return (
            <div>
                {pageContent}
            </div>
        );
    }
}