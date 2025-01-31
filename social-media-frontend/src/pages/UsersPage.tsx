import React, { useState } from 'react';
import UserList from '../components/UserList';
import UserForm from '../components/UserForm';

const UsersPage: React.FC = () => {
  const [showForm, setShowForm] = useState(false);

  return (
    <div>
      <h1>Users Management</h1>
      <button onClick={() => setShowForm(!showForm)}>
        {showForm ? 'Hide Form' : 'Add User'}
      </button>
      {showForm && <UserForm onSuccess={() => setShowForm(false)} />}
      <UserList />
    </div>
  );
};

export default UsersPage;