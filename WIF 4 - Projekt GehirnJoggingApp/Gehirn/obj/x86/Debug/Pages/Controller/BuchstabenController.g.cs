﻿#pragma checksum "C:\Users\Daniel Notebook\Documents\Git\schule-studium-programming\WIF 4 - Projekt GehirnJoggingApp\Gehirn\Pages\Controller\BuchstabenController.xaml" "{406ea660-64cf-4c82-b6f0-42d48172a799}" "7428674E39656BA5428DF3790F96FB0A"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace GehirnJogging.Pages.Controller
{
    partial class BuchstabenController : 
        global::Windows.UI.Xaml.Controls.UserControl, 
        global::Windows.UI.Xaml.Markup.IComponentConnector,
        global::Windows.UI.Xaml.Markup.IComponentConnector2
    {
        /// <summary>
        /// Connect()
        /// </summary>
        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Windows.UI.Xaml.Build.Tasks"," 10.0.17.0")]
        [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
        public void Connect(int connectionId, object target)
        {
            switch(connectionId)
            {
            case 2: // Pages\Controller\BuchstabenController.xaml line 12
                {
                    this.baseGrid = (global::Windows.UI.Xaml.Controls.Grid)(target);
                }
                break;
            case 3: // Pages\Controller\BuchstabenController.xaml line 14
                {
                    this.Count = (global::Windows.UI.Xaml.Controls.TextBlock)(target);
                }
                break;
            case 4: // Pages\Controller\BuchstabenController.xaml line 15
                {
                    this.WortDisplay = (global::Windows.UI.Xaml.Controls.TextBlock)(target);
                }
                break;
            case 5: // Pages\Controller\BuchstabenController.xaml line 23
                {
                    this.Weiter = (global::Windows.UI.Xaml.Controls.Button)(target);
                    ((global::Windows.UI.Xaml.Controls.Button)this.Weiter).Click += this.Aufgeben;
                }
                break;
            case 6: // Pages\Controller\BuchstabenController.xaml line 20
                {
                    this.vorgabeStack = (global::Windows.UI.Xaml.Controls.StackPanel)(target);
                }
                break;
            case 7: // Pages\Controller\BuchstabenController.xaml line 17
                {
                    this.eingabeStack = (global::Windows.UI.Xaml.Controls.StackPanel)(target);
                }
                break;
            default:
                break;
            }
            this._contentLoaded = true;
        }

        /// <summary>
        /// GetBindingConnector(int connectionId, object target)
        /// </summary>
        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Windows.UI.Xaml.Build.Tasks"," 10.0.17.0")]
        [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
        public global::Windows.UI.Xaml.Markup.IComponentConnector GetBindingConnector(int connectionId, object target)
        {
            global::Windows.UI.Xaml.Markup.IComponentConnector returnValue = null;
            return returnValue;
        }
    }
}
