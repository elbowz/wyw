<template>
  <div>
  <div class="p-3 bg-secondary progress-bar-striped" style="min-height: 170px;">
    <b-button class="mb-2" variant="primary" @click="$bvToast.show('example-toast')">
      Show toast
    </b-button>
    <b-toast id="example-toast" title="BootstrapVue" static no-auto-hide>dkjfskj fdkfjdskfj sdddsdfffffffffk
      Hello, world! This is a toast message.
    </b-toast>
  </div>
  <button class="btn btn-outline-secondary">
    Notifications <span class="badge badge-secondary">10</span>
  </button>
    <!-- Checked Custom Toggle -->
    <div class="custom-control custom-toggle my-2">
      <input type="checkbox" id="customToggle1" name="customToggle1" class="custom-control-input" checked>
      <label class="custom-control-label" for="customToggle1">I'm already toggled!</label>
    </div>

    <!-- Normal Custom Toggle -->
    <div class="custom-control custom-toggle my-2">
      <input type="checkbox" id="customToggle2" name="customToggle2" class="custom-control-input">
      <label class="custom-control-label" for="customToggle2">Toggle me!</label>
    </div>

    <!-- Disabled Custom Toggle -->
    <div class="custom-control custom-toggle my-2">
      <input type="checkbox" id="customToggle3" name="customToggle3" class="custom-control-input" disabled>
      <label class="custom-control-label" for="customToggle3">Oh no! I'm disabled!</label>
    </div>
  </diV>
</template>
