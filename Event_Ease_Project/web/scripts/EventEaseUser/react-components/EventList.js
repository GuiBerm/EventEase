'use strict';

class EventList extends React.Component {

	llamadaAjax(eventId) {
		const requestData = {
			eventId: eventId,
		};

		fetch('http://localhost:8080/EventEase/SellTicket', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(requestData)
		})
			.then(
				alert("Ticket successfully acquired")
			)
			.catch(error => {
				console.error('There was an error making the request:', error);
			});
	}

	render() {
		if (Array.isArray(this.props.eventList) && this.props.eventList.length > 0) {
			return (
				<div>
					<ul>
						{this.props.eventList.map((element) => (
							<li key={element.eventId} style={{ border: '5px solid' }}>
								{element.name}/
								{element.city}/
								{element.date}/
								{element.numberOfTickets}/
								{element.price}
								<button onClick={() => this.llamadaAjax(element.eventId)}>Buy</button>
							</li>
						))}
					</ul>
				</div>
			);
		} else {
			return null;
		}
	}
}
