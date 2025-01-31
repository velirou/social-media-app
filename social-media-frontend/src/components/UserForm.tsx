import React, { useState } from 'react';
import { createUser, updateUser } from '../services/userService';

const UserForm: React.FC<{ user?: any; onSuccess: () => void }> = ({ user, onSuccess }) => {
  const [name, setName] = useState(user?.name || '');
  const [email, setEmail] = useState(user?.email || '');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (user) {
      await updateUser(user.id, { name, email });
    } else {
      await createUser({ name, email });
    }
    onSuccess();
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="text" value={name} onChange={e => setName(e.target.value)} placeholder="Name" />
      <input type="email" value={email} onChange={e => setEmail(e.target.value)} placeholder="Email" />
      <button type="submit">{user ? 'Update' : 'Create'}</button>
    </form>
  );
};

export default UserForm;