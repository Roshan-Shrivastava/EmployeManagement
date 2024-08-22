document.getElementById('employeeForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const id = document.getElementById('employeeId').value;
    const name = document.getElementById('name').value;
    const Mobile_No = document.getElementById('Mobile_No').value;

    const employee = { name: name, Mobile_No: Mobile_No };

    if (id) {
        updateEmployee(id, employee);
    } else {
        createEmployee(employee);
    }
});

function createEmployee(employee) {
    fetch('/employees', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(employee)
    })
        .then(response => response.text())
        .then(data => {
            alert(data);
            resetForm();
            fetchEmployees();
        });
}

function updateEmployee(id, employee) {
    fetch(`/employees/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(employee)
    })
        .then(response => response.text())
        .then(data => {
            alert(data);
            resetForm();
            fetchEmployees();
        });
}

function fetchEmployees() {
    fetch('/employees')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('#employeeTable tbody');
            tableBody.innerHTML = '';
            data.forEach(employee => {
                tableBody.innerHTML += `
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.name}</td>
                        <td>${employee.Mobile_No}</td>
                        <td class="actions">
                            <button onclick="editEmployee(${employee.id})">Edit</button>
                            <button class="delete" onclick="deleteEmployee(${employee.id})">Delete</button>
                        </td>
                    </tr>
                `;
            });
        });
}

function deleteEmployee(id) {
    fetch(`/employees/${id}`, {
        method: 'DELETE'
    })
        .then(response => response.text())
        .then(data => {
            alert(data);
            fetchEmployees();
        });
}

function editEmployee(id) {
    fetch(`/employeesFind/${id}`)
        .then(response => response.json())
        .then(employee => {
            document.getElementById('employeeId').value = employee.id;
            document.getElementById('name').value = employee.name;
            document.getElementById('Mobile_No').value = employee.Mobile_No;
        });
}

function resetForm() {
    document.getElementById('employeeId').value = '';
    document.getElementById('name').value = '';
    document.getElementById('Mobile_No').value = '';
}
