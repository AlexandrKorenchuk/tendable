package com.example.core_ui.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.release.core_ui.utilis.DisplayDensity
import com.example.core_ui.utilis.NavigationEvent
import com.example.core_ui.utilis.ShowDialog
import com.release.core_ui.utilis.SnackbarCustom
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var displayDensity: DisplayDensity

    protected var isShownSnackBar: Boolean = false

    protected var isDialogShown: Boolean = false

    private lateinit var snackBar: Snackbar

    private lateinit var dialog: Dialog

    fun showSnackBar(snackBarEvent: SnackbarCustom) {
        when (snackBarEvent) {
            is SnackbarCustom.Success -> {
                snackBar =
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        snackBarEvent.success,
                        1000
                    )
                snackBar.show()
            }
            is SnackbarCustom.Error -> {
                snackBar =
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        snackBarEvent.error,
                        1000
                    )
                snackBar.show()
            }
        }
    }

    fun showDialog(showDialogEvent: ShowDialog) {
        when (showDialogEvent) {
            is ShowDialog.ExceptionDialog -> {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setMessage(showDialogEvent.content)
                dialogBuilder.setCancelable(true)
                val alert = dialogBuilder.create()
                alert.show()
            }
            is ShowDialog.SuccessDialog -> {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setMessage(showDialogEvent.content)
                dialogBuilder.setCancelable(true)
                val alert = dialogBuilder.create()
                alert.show()
            }
        }
    }

    fun navigate(navigationEvent: NavigationEvent) {
        when (navigationEvent) {
            is NavigationEvent.Forward -> {
                navigate(navigationEvent.navigate)
            }
            is NavigationEvent.Pop -> {
                popBackStack(navigationEvent.navigate, navigationEvent.inclusive)
            }
            is NavigationEvent.ForwardWithPopUp -> {
                navigateWithPopUp(navigationEvent.popUpTo, navigationEvent.navigate)
            }
            is NavigationEvent.ReplaceGraph -> {
                setGraph(navigationEvent.setGraph)
            }
            is NavigationEvent.ForwardWithBundle -> {
                val bundle = bundleOf(
                    "bundle" to navigationEvent.bundle
                )
                navigateBundle(navigationEvent.navigate, bundle)
            }
        }
    }

    private fun navigateWithPopUp(id: Int, it: Int) {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(id, true)
            .build()
        findNavController().navigate(
            it,
            null,
            navOptions
        )
    }

    private fun navigate(it: Int) {
        findNavController().navigate(it)
    }

    private fun popBackStack(id: Int, boolean: Boolean) {
        findNavController().popBackStack(
            id,
            boolean
        )
    }

    private fun setGraph(it: Int) {
        findNavController().setGraph(it)
    }

    private fun navigateBundle(it: Int, bundle: Bundle) {
        findNavController().navigate(it, bundle)
    }

    override fun onDestroyView() {
        if (isShownSnackBar) {
            snackBar.dismiss()
        }
        if (isDialogShown) {
            dialog.cancel()
        }
        super.onDestroyView()
    }
}
