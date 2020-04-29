<template>
  <div v-if="users.length">
    <div
      v-for="user in users"
      :key="user.id"
    >
      <router-link :to="{ name: 'user', params: { id: user.id }}">{{ user.firstName }}</router-link>
    </div>
  </div>
  <div v-else>
    <h2>No films</h2>
  </div>
</template>

<script>
export default {
  name: 'UserList',
  props: {
    users: Array,
  },
};
</script>
