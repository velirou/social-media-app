import React, { useState } from 'react';
import PostList from '../components/PostList';
import PostForm from '../components/PostForm';

const PostsPage: React.FC = () => {
  const [showForm, setShowForm] = useState(false);
  const [selectedPost, setSelectedPost] = useState<any>(null);

  const handleEdit = (post: any) => {
    setSelectedPost(post);
    setShowForm(true);
  };

  const handleFormSuccess = () => {
    setShowForm(false);
    setSelectedPost(null);
  };

  return (
    <div>
      <h1>Posts Management</h1>
      <button onClick={() => setShowForm(!showForm)}>
        {showForm ? 'Hide Form' : 'Add Post'}
      </button>
      {showForm && (
        <PostForm post={selectedPost} onSuccess={handleFormSuccess} />
      )}
      <PostList onEdit={handleEdit} />
    </div>
  );
};

export default PostsPage;